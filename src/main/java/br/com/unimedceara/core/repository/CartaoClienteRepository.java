package br.com.unimedceara.core.repository;

import br.com.unimedceara.core.model.CartaoCliente;
import br.com.unimedceara.core.model.CartaoClientePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoClienteRepository extends JpaRepository<CartaoCliente, CartaoClientePk>{

    @Query(value = "SELECT * FROM UNMVMCARD.VM_CARD vc WHERE vc.CPF_RESP_FINANCEIRO=:cpf OR vc.CPF_TITULAR=:cpf", nativeQuery = true)
    List<CartaoCliente> numCarteirasByCpfRespFinanceiroOrTitular(@Param("cpf") Long cpf);
	
}
