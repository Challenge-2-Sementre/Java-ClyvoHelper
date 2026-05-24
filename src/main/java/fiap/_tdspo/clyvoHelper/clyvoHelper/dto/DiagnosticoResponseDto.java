package fiap._tdspo.clyvoHelper.clyvoHelper.dto;

public class DiagnosticoResponseDto {
    private String diagnostico;

    public DiagnosticoResponseDto() {
    }

    public DiagnosticoResponseDto(
            String diagnostico
    ) {
        this.diagnostico = diagnostico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(
            String diagnostico
    ) {
        this.diagnostico = diagnostico;
    }
}
