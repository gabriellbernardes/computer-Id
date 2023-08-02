package br.com.unimedceara.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CartaoClientePk.class)
@Table(name = "vm_card", schema = "UNMVMCARD")
public class CartaoCliente {

	@Id
	@Column(name = "UNIMED_CARTEIRA")
	private BigDecimal unimedCarteira;

	@Id
	@Column(name = "COD_CARTEIRA")
	private BigDecimal codCarteira;

	@Id
	@Column(name = "DV_CARTEIRA")
	private String dvCarteira;

	public CartaoClientePk getId() {
		return new CartaoClientePk(codUnimed, dvCarteira, codCarteira);
	}
	
	@Column(name = "COD_BENEFICIARIO")
	private String codigoBeneficiario;

	@Column(name = "COD_UNIMED")
	private BigDecimal codUnimed;

	@Column(name = "COD_EMPRESA")
	private BigDecimal codEmpresa;

	@Column(name = "COD_FAMILIA")
	private BigDecimal codFamilia;

	@Column(name = "COD_CONTRATO")
	private BigDecimal codContrato;

	@Column(name = "COD_ARQUIVO")
	private BigDecimal codArquivo;

	@Column(name = "NUMERACAO", nullable = false)
	private String numeracao;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "COD_DEPENDENCIA")
	private String codigoDependencia;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_NASC")
	private Date dataNasc;

	@Column(name = "RG")
	private BigDecimal rg;

	@Column(name = "CPF")
	private BigDecimal cpf;

	@Column(name = "CPF_TITULAR")
	private BigDecimal cpfTitular;

	@Column(name = "NATUREZA_CONTRATACAO")
	private String naturezaContratacao;

	@Column(name = "ACOMODACAO", nullable = false)
	private String acomodacao;

	@Column(name = "VALIDADE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date validade;

	@Column(name = "NOME_BENEFICIARIO")
	private String nomeBeneficiario;

	@Column(name = "REDE_ATENDIMENTO")
	private String redeAtendimento;

	@Column(name = "ATEND", nullable = false)
	private String atend;

	@Column(name = "PLANO_REGULAMENTADO")
	private String planoRegulamentado;

	@Column(name = "LBL_REGULAMENTADO")
	private String lblRegulamentado;

	@Column(name = "ABRANGENCIA", nullable = false)
	private String abrangencia;

	@Column(name = "VIA", nullable = false)
	private String via;

	@Column(name = "CPT", nullable = false)
	private String cpt; 

	@Column(name = "CONTRATANTE", nullable = false)
	private String contratante;

	@Column(name = "ABRANGENCIA_LOCAL_REGIONAL")
	private String abrangenciaLocalRegional;

	@Column(name = "COD_PROD_ANS")
	private String codProdAns;

	@Column(name = "TRILHA_01")
	private String trilha01;

	@Column(name = "TRILHA_02")
	private String trilha02;

	@Column(name = "DATA_ULTIMA_ATUALIZACAO")
	@Temporal(TemporalType.DATE)
	private Date dataUltimaAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_NASC_TITULAR")
	private Date dataNascTitular;

	@Column(name = "CNS")
	private String cns;

	@Temporal(TemporalType.DATE)
	@Column(name = "VIGENCIA")
	private Date vigencia;

	@Column(name = "SEGMENTACAO")
	private String segmentacao;

	@Column(name = "ADM_BENEFICIO")
	private String admBeneficio;

	@Column(name = "DATA_EMISSAO")
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;

	@Column(name = "RESP_FINANCEIRO")
	private String responsavelFinanceiro;

	@Column(name = "CPF_RESP_FINANCEIRO")
	private BigDecimal cpfResponsavelFinanceiro;

	@Column(name = "DT_NASC_RESP_FINANCEIRO")
	@Temporal(TemporalType.DATE)
	private Date dataNascimentoResponsavelFinanceiro;

	@Column(name = "TIPO_CARTAO")
	private String tipoCartao;

	@Column(name = "NOME_UNIMED")
	private String nomeUnimed;

	@Column(name = "NUM_ANS")
	private String numAns;

	@Column(name = "SITE")
	private String site;

	@Column(name = "FONE_SAC")
	private String foneSac;

	@Column(name = "VERSAO_LAYOUT")
	private String versaoLayout;

	@Column(name = "FLG_AVISO_SMS")
	private String flgAvisoSMS;

	@Temporal(TemporalType.DATE)
	@Column(name = "DAT_CARENCIA_01")
	private Date datCarencia01;

	@Temporal(TemporalType.DATE)
	@Column(name = "DAT_CARENCIA_02")
	private Date datCarencia02;

	@Temporal(TemporalType.DATE)
	@Column(name = "DAT_CARENCIA_03")
	private Date datCarencia03;

	@Temporal(TemporalType.DATE)
	@Column(name = "DAT_CARENCIA_04")
	private Date datCarencia04;

	@Temporal(TemporalType.DATE)
	@Column(name = "DAT_CARENCIA_05")
	private Date datCarencia05;

	@Temporal(TemporalType.DATE)
	@Column(name = "DAT_CARENCIA_06")
	private Date datCarencia06;

	@Temporal(TemporalType.DATE)
	@Column(name = "DAT_CARENCIA_07")
	private Date datCarencia07;

	@Temporal(TemporalType.DATE)
	@Column(name = "DAT_CARENCIA_08")
	private Date datCarencia08;

	@Temporal(TemporalType.DATE)
	@Column(name = "DAT_CARENCIA_09")
	private Date datCarencia09;

	@Temporal(TemporalType.DATE)
	@Column(name = "DAT_CARENCIA_10")
	private Date datCarencia10;

	@Column(name = "DSC_CARENCIA_01")
	private String dscCarencia01;

	@Column(name = "DSC_CARENCIA_02")
	private String dscCarencia02;

	@Column(name = "DSC_CARENCIA_03")
	private String dscCarencia03;

	@Column(name = "DSC_CARENCIA_04")
	private String dscCarencia04;

	@Column(name = "DSC_CARENCIA_05")
	private String dscCarencia05;

	@Column(name = "DSC_CARENCIA_06")
	private String dscCarencia06;

	@Column(name = "DSC_CARENCIA_07")
	private String dscCarencia07;

	@Column(name = "DSC_CARENCIA_08")
	private String dscCarencia08;

	@Column(name = "DSC_CARENCIA_09")
	private String dscCarencia09;

	@Column(name = "DSC_CARENCIA_10")
	private String dscCarencia10;
	
	@Column(name = "DAT_GERACAO_ARQUIVO")
	@Temporal(TemporalType.DATE)
	private Date datGeracaoArquivo;
	
	@Column(name = "COD_REDE_ATEND")
	private BigDecimal codRedeAtend;
	
	@Column(name = "COD_PLANO")
	private String codPlano;
	
	@Column(name = "NOME_SOCIAL")
	private String nomeSocial;

	@Column(name = "NOME_SOCIAL_ABREV")
	private String nomeSocialAbrev;

	@Column(name = "NOME_UNIMED_CONTRATADA")
	private String nomeUnimedContratada;
	
	@Column(name = "COD_UNIMED_CONTRATADA")
	private BigDecimal codUnimedContratada;
	
	@Column(name = "NUM_ANS_UNIMED_CONTRATADA")
	private String numAnsUnimedContratada;
	
	@Column(name = "COD_BENEF_COOPERADO")
	private BigDecimal codBenefCooperado;
	
	@Column(name = "SEXO")
	private String sexo;
	
	@Column(name = "FLG_AVISO_WAPP")
	private String flgAvisoWapp;
	
	@Column(name = "DAT_AVISO_WAPP")
	private Date datAvisoWapp;
	
	@Column(name = "SEQ_SERVICE")
	private BigDecimal seqService;
	
	@Column(name = "SERVICO")
	private BigDecimal servico;
	
	@Column(name = "COD_TIPO_CONTRATO")
	private Integer codTipoContrato;
	
	@Column(name = "PF_PJ")
	private String pfPj;

	@Transient
	private String qrcode;

	@Transient
	private String qrcodeText;

	@Transient
	private Long diffTime;

	@Transient
	private Long codVMCard;

	@Transient
	private Long serviceID;

	@Transient
	private String tituloCarteira;

	@Transient
	private boolean sucesso;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codCarteira == null) ? 0 : codCarteira.hashCode());
		result = prime * result + ((dvCarteira == null) ? 0 : dvCarteira.hashCode());
		result = prime * result + ((unimedCarteira == null) ? 0 : unimedCarteira.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaoCliente other = (CartaoCliente) obj;
		if (codCarteira == null) {
			if (other.codCarteira != null)
				return false;
		} else if (!codCarteira.equals(other.codCarteira))
			return false;
		if (dvCarteira == null) {
			if (other.dvCarteira != null)
				return false;
		} else if (!dvCarteira.equals(other.dvCarteira))
			return false;
		if (unimedCarteira == null) {
			if (other.unimedCarteira != null)
				return false;
		} else if (!unimedCarteira.equals(other.unimedCarteira))
			return false;
		return true;
	}
	
	
	public boolean isEqualToCpfTitular(final Long cpf) {
		if(cpf != null) {
			BigDecimal cpff = BigDecimal.valueOf(cpf);
			return cpff.equals(this.cpfTitular) ? true : false;
		}else {
			return false;
		}
	}
	
	public boolean isPessoaFisica() {
		return this.pfPj != null && this.pfPj.equals("F"); 
	}
	
	public boolean isPessoaJuridica() {
		return this.pfPj != null && this.pfPj.equals("J"); 
	}

}
