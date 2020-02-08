package dev.fujioka.juan.infrastructure.specification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import dev.fujioka.juan.infrastructure.specification.annotation.SearchParam;

@Component
public class SpecificationBuilder {

	public <T> Specification<T> criarFiltro(Map<String, String> filtros, Class<T> data) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			filtros.forEach((k, v) -> {
				try {
					if (data.getDeclaredField(k) != null) {
						predicates.add(criteriaBuilder.equal(root.get(k), v));
					}
				} catch (NoSuchFieldException | SecurityException e) {
					e.printStackTrace();
				}
			});

			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

	public <T> Specification<T> criarFiltro(Object objeto, Class<T> data) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			Field[] declaredFields = objeto.getClass().getDeclaredFields();
			for (Field field : declaredFields) {

				if (field.isAnnotationPresent(SearchParam.class)) {
					try {
						field.setAccessible(true);
						Object value = field.get(objeto);
						if (value == null) {
							continue;
						}
						SearchParam parametros = field.getAnnotation(SearchParam.class);
						if (value instanceof Number) {
							predicates.add(getPredicate(root, criteriaBuilder, parametros,Integer.valueOf(value.toString())));
						} else if (value instanceof List){
							predicates.add(getPredicate(root, criteriaBuilder, parametros,(List<?>) value));
						}else {
							predicates.add(getPredicate(root, criteriaBuilder, parametros, value.toString()));
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}

				}
			}
			if (predicates.isEmpty()) {
				return null;
			}
			return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
	
	private Predicate getPredicate(Root<?> root, CriteriaBuilder criteriaBuilder, SearchParam parametros, Integer value) {
		switch (parametros.operacao()) {
		case EQUALS:
			return criteriaBuilder.equal(root.get(parametros.propriedade()), value);
		case GREATER_THAN_OR_EQUAL:
            return criteriaBuilder.greaterThanOrEqualTo(root.get(parametros.propriedade()), value);
        case LESS_THAN_OR_EQUAL:
            return criteriaBuilder.lessThanOrEqualTo(root.get(parametros.propriedade()), value);
		default:
			return criteriaBuilder.equal(root.get(parametros.propriedade()), value);
		}
	}
	
	private Predicate getPredicate(Root<?> root, CriteriaBuilder criteriaBuilder, SearchParam parametros, String value) {
		switch (parametros.operacao()) {
		case EQUALS:
			return criteriaBuilder.equal(criteriaBuilder.lower(root.get(parametros.propriedade())), value.toLowerCase());
		case LIKE:
			return criteriaBuilder.like(criteriaBuilder.lower(root.get(parametros.propriedade())), "%" + value + "%");
		default:
			return criteriaBuilder.equal(root.get(parametros.propriedade()), value);
		}
	}
	
	private Predicate getPredicate(Root<?> root, CriteriaBuilder criteriaBuilder, SearchParam parametros,List<?> value) {
		In<Object> in = criteriaBuilder.in(root.get(parametros.propriedade()));
		value.forEach(v -> in.value(v));
		return in;
	}




}
