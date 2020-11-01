package br.com.javatravelers.JavaTravelers.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javatravelers.JavaTravelers.domain.model.FavoritesModel;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoritesModel, Integer> {

	public List<FavoritesModel> findByCompanhiaAndUser_Id(String company, Integer id);
	
	public List<FavoritesModel> findByCidadeOrigemAndUser_Id(String origin, Integer id);
}
