public class DocumentoFiscal {
    private String xmlContent;
    private String certificado;
    private double valorImpostos;
    private String numeroNota;

    public DocumentoFiscal(String xml, String cert, double impostos, String num) {
        this.xmlContent = xml;
        this.certificado = cert;
        this.valorImpostos = impostos;
        this.numeroNota = num;
    }
    
    public String getXmlContent() { 
        return xmlContent; 
    }
    public String getCertificado() { 
        return certificado; 
    }
    public double getValorImpostos() { 
        return valorImpostos; 
    }
    public String getNumeroNota() { 
        return numeroNota; 
    }
}