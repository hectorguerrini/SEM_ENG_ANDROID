package semanaeng.studio.com.semanadeengenhariamaua.modelo;

/**
 * Created by hecto on 15/05/2017.
 */

public class patrocinador {
    private Integer id;
    private String nome;
    private Integer tier;
    private String imagem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
