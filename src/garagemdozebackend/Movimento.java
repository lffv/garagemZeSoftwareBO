
package garagemdozebackend;

import java.util.GregorianCalendar;

public class Movimento {
    
    private int cod;
    private String user;
    private GregorianCalendar data;
    private double valor;
    private double ganho;

    public Movimento(int cod, String user, GregorianCalendar data, double valor, double ganho) {
        this.cod = cod;
        this.user = user;
        this.data = data;
        this.valor = valor;
        this.ganho = ganho;
    }
    
    public int getCod() {
        return cod;
    }

    public String getUser() {
        return user;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
    
    public double getGanho() {
        return ganho;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public void setGanho(double ganho) {
        this.ganho = ganho;
    }
    
}
