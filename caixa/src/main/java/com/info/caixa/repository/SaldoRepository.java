package com.info.caixa.repository;

import com.info.caixa.model.Saldo;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SaldoRepository extends JpaRepository<Saldo, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE saldo SET valor = valor + :vm WHERE data >= :data", nativeQuery = true)
    public void atualizaSaldo(@Param("vm") Double valor, @Param("data") Date data);

    @Query("SELECT s.valor FROM Saldo s WHERE s.data < :data ORDER BY s.data")
    public List<Double> pegaValorAnterior(@Param("data") Date data);

    @Query("SELECT s.valor FROM Saldo s WHERE s.data <= :data ORDER BY s.data")
    public List<Double> pegaValorFinal(@Param("data") Date data);

    List<Saldo> findByData(Date data);
}
