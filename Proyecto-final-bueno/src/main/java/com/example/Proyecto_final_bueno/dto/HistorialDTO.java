package com.example.Proyecto_final_bueno.dto;


import java.time.LocalDate;
import java.util.List;

public class HistorialDTO {

    private Long idUsuario;
    private List<PrestamoDTO> prestamos;
    private List<ReservaDTO> reservas;

    // Getters y Setters

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<PrestamoDTO> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<PrestamoDTO> prestamos) {
        this.prestamos = prestamos;
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    public static class PrestamoDTO {
        private Long idPrestamo;
        private String tituloLibro;
        private LocalDate fechaPrestamo;
        private LocalDate fechaDevolucion;

        // Getters y Setters

        public Long getIdPrestamo() {
            return idPrestamo;
        }

        public void setIdPrestamo(Long idPrestamo) {
            this.idPrestamo = idPrestamo;
        }

        public String getTituloLibro() {
            return tituloLibro;
        }

        public void setTituloLibro(String tituloLibro) {
            this.tituloLibro = tituloLibro;
        }

        public LocalDate getFechaPrestamo() {
            return fechaPrestamo;
        }

        public void setFechaPrestamo(LocalDate fechaPrestamo) {
            this.fechaPrestamo = fechaPrestamo;
        }

        public LocalDate getFechaDevolucion() {
            return fechaDevolucion;
        }

        public void setFechaDevolucion(LocalDate fechaDevolucion) {
            this.fechaDevolucion = fechaDevolucion;
        }
    }

    public static class ReservaDTO {
        private Long idReserva;
        private String tituloLibro;
        private LocalDate fechaReserva;

        // Getters y Setters

        public Long getIdReserva() {
            return idReserva;
        }

        public void setIdReserva(Long idReserva) {
            this.idReserva = idReserva;
        }

        public String getTituloLibro() {
            return tituloLibro;
        }

        public void setTituloLibro(String tituloLibro) {
            this.tituloLibro = tituloLibro;
        }

        public LocalDate getFechaReserva() {
            return fechaReserva;
        }

        public void setFechaReserva(LocalDate fechaReserva) {
            this.fechaReserva = fechaReserva;
        }
    }
}
