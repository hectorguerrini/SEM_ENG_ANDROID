package semanaeng.studio.com.semanadeengenhariamaua.modelo;

/**
 * Created by hecto on 16/06/2017.
 */

public class mVisita {
    private int Id;
    private String Codigo;
    private String Empresa;
    private String Local;
    private String Data;
    private String HoraInicio;
    private String HoraFim;
    private String Imagem;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        HoraInicio = horaInicio;
    }

    public String getHoraFim() {
        return HoraFim;
    }

    public void setHoraFim(String horaFim) {
        HoraFim = horaFim;
    }

    public String getImagem() {
        return Imagem;
    }

    public void setImagem(String imagem) {
        Imagem = imagem;
    }
}
