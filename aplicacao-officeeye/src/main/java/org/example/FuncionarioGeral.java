package org.example;

public class FuncionarioGeral extends Funcionario{
    private String area;
    private String statusLogin;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(String statusLogin) {
        this.statusLogin = statusLogin;
    }

    @Override
    public String toString() {
        return "FuncionarioGeral{" +
                "area='" + area + '\'' +
                ", statusLogin='" + statusLogin + '\'' +
                "} " + super.toString();
    }
}
