package br.com.javatravelers.JavaTravelers.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.javatravelers.JavaTravelers.domain.model.FavoriteModel;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteModel, Integer> {
	
	public List<FavoriteModel> findAllByUserId(int id);
	
	public FavoriteModel findByIdAndUserId(int id, int userId);
	public boolean existsByIdAndUserId(int id, int userId);
}
