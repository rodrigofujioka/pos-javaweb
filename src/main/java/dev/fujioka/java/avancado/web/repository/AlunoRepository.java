package dev.fujioka.java.avancado.web.repository;

import dev.fujioka.java.avancado.web.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    @Query("select a from Aluno a order by a.nome ASC")
    public List<Aluno> listarOrdernadoPorNome();
    public List<Aluno> findAllByOrderByNomeAsc();

    @Query("select a from Aluno a where a.nome like %:nome% ")
    public List<Aluno> buscarAlunoPorNomeLike(@Param("nome") String nome);
    public Aluno findByMatricula(String matricula);

    public Aluno findByMatriculaAndNome(String matricula, String nome);

}
