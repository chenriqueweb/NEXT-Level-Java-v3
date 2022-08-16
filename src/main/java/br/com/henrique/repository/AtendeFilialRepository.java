package br.com.henrique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.henrique.model.AtendeFilial;

@Repository
public interface AtendeFilialRepository extends JpaRepository<AtendeFilial, Integer>  {

//	public List<AtendeFilial> findByAtende(Atende atende);

//	public List<AtendeFilial> findByAtendeFilial(Atende atende);

}
