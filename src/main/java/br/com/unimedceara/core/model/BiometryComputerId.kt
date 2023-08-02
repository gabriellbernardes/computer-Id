package br.com.unimedceara.core.model

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMPUTER_ID_USER", schema = "SH_FACE_ID")
class BiometryComputerId (
    @Id
    @Column(name = "CARD", nullable = false)
    var card: String? = null,

    @Lob
    @Column(name = "BASE64IMAGE", nullable = true, )
    var fotoB64: String? = null,

    @Column(name = "NAME", nullable = true)
    var name: String? = null

)