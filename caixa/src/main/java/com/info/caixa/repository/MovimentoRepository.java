package com.info.caixa.repository;

import com.info.caixa.model.Extrato;
import com.info.caixa.model.Movimento;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovimentoRepository extends JpaRepository<Movimento, Integer> {
    @Query("select m from Movimento m where m.data between :di and :df order by m.data")
    public List<Movimento> listaExtrato(@Param("di") Date dataInicial,@Param("df") Date dataFinal);
}