package br.com.javatravelers.JavaTravelers.domain.model.dto;

import java.util.List;

import com.gama.passagens.project.model.cadastro.Viajante;
import com.gama.passagens.project.model.reserva.Reserva;

public class ViajanteReserva {
	private Viajante viajante;
	private List<Reserva> reservas;
	public Viajante getViajante() {
		return viajante;
	}
	public void setViajante(Viajante viajante) {
		this.viajante = viajante;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
}
