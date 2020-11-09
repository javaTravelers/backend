package br.com.javatravelers.JavaTravelers.domain.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.javatravelers.JavaTravelers.domain.model.acesso.Role;

public interface RoleRepository extends CrudRepository<Role, String> {

	
}
