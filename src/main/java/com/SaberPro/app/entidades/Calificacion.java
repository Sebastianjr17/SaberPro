package com.SaberPro.app.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import jakarta.validation.constraints.NotEmpty;

@Document(collection = "calificacion")
public class Calificacion {

	@Id
	private String id;

	@DocumentReference
	private Estudiante estudiante;

	private String anulado;

	@NotEmpty
	private String puntajeTotal;

	@NotEmpty
	private String comEscrita;

	@NotEmpty
	private String razonCuantitativo;

	@NotEmpty
	private String lecturaCritica;

	@NotEmpty
	private String compeCiudadanas;

	@NotEmpty
	private String ingles;

	@NotEmpty
	private String formProyectos;

	@NotEmpty
	private String penCientifico;

	@NotEmpty
	private String disenoSoftware;

	// CONSTRUCTOR
	public Calificacion() {

	}

	// METODOS GET y SET
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public String getAnulado() {
		return anulado;
	}

	public void setAnulado(String anulado) {
		this.anulado = anulado;
	}

	public String getPuntajeTotal() {
		return puntajeTotal;
	}

	public void setPuntajeTotal(String puntajeTotal) {
		this.puntajeTotal = puntajeTotal;
	}

	public String getComEscrita() {
		return comEscrita;
	}

	public void setComEscrita(String comEscrita) {
		this.comEscrita = comEscrita;
	}

	public String getRazonCuantitativo() {
		return razonCuantitativo;
	}

	public void setRazonCuantitativo(String razonCuantitativo) {
		this.razonCuantitativo = razonCuantitativo;
	}

	public String getLecturaCritica() {
		return lecturaCritica;
	}

	public void setLecturaCritica(String lecturaCritica) {
		this.lecturaCritica = lecturaCritica;
	}

	public String getCompeCiudadanas() {
		return compeCiudadanas;
	}

	public void setCompeCiudadanas(String compeCiudadanas) {
		this.compeCiudadanas = compeCiudadanas;
	}

	public String getIngles() {
		return ingles;
	}

	public void setIngles(String ingles) {
		this.ingles = ingles;
	}

	public String getFormProyectos() {
		return formProyectos;
	}

	public void setFormProyectos(String formProyectos) {
		this.formProyectos = formProyectos;
	}

	public String getPenCientifico() {
		return penCientifico;
	}

	public void setPenCientifico(String penCientifico) {
		this.penCientifico = penCientifico;
	}

	public String getDisenoSoftware() {
		return disenoSoftware;
	}

	public void setDisenoSoftware(String disenoSoftware) {
		this.disenoSoftware = disenoSoftware;
	}
}
