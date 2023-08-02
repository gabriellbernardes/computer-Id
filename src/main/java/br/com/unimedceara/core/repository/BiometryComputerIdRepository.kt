package br.com.unimedceara.core.repository

import br.com.unimedceara.core.model.BiometryComputerId
import org.springframework.data.jpa.repository.JpaRepository

interface BiometryComputerIdRepository : JpaRepository<BiometryComputerId?, String?>{

}