package edu.eam.ingesoft.ejemploback.repositories;

import edu.eam.ingesoft.ejemploback.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, String> {

    @Query("SELECT t FROM Transaccion t  WHERE t.numeroCuenta = :numeroCuenta")
    List<Transaccion> buscarTransaccionesCuenta(String numeroCuenta);
}
