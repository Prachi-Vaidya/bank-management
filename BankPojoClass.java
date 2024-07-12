public class BankPojoClass {
    private int acNo;
    private String cName;
    private String branch;
    private String acType;
    private double acbalance;

    public BankPojoClass(String cName, String branch, String acType, double acbalance) {
        this.cName = cName;
        this.branch = branch;
        this.acType = acType;
        this.acbalance = acbalance;
    }

    public int getAcNo() {
        return acNo;
    }

    public void setAcNo(int acNo) {
        this.acNo = acNo;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }

    public double getAcbalance() {
        return acbalance;
    }

    public void setAcbalance(double acbalance) {
        this.acbalance = acbalance;
    }

    @Override
    public String toString() {
        return "BankUserClass{" +
                "acNo=" + acNo +
                ", cName='" + cName + '\'' +
                ", branch='" + branch + '\'' +
                ", acType='" + acType + '\'' +
                ", acbalance=" + acbalance +
                '}';
    }
}
