package view;

public class Member
{
    private String nam;
    private String correo;

    public Hum(String nam, String correo)
    {
        this.nam = nam;
        this.correo = correo;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNam() {
        return nam;
    }

    public String getCorreo() {
        return correo;
    }

    public String toString()
    {
        return "Name: " +  nam + "      " + "Mail: " + correo;
    }
}
}


