package fi.tarina.tarinamittaus.Specification;


public class MittausSearchParameters {

    private String  searchKeyword;
    private Double squareArea;
    private Integer constructionYear;
    private QueryOperator operatorForSquareArea;
    private QueryOperator operatorForConstructionYear;

    public MittausSearchParameters() {
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public MittausSearchParameters(String searchKeyword, Double squareArea, Integer constructionYear, QueryOperator operatorForSquareArea, QueryOperator operatorForConstructionYear) {
        this.searchKeyword = searchKeyword;
        this.squareArea = squareArea;
        this.constructionYear = constructionYear;
        this.operatorForSquareArea = operatorForSquareArea;
        this.operatorForConstructionYear = operatorForConstructionYear;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public Double getSquareArea() {
        return squareArea;
    }

    public void setSquareArea(Double squareArea) {
        this.squareArea = squareArea;
    }

    public Integer getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    public QueryOperator getOperatorForSquareArea() {
        return operatorForSquareArea;
    }

    public void setOperatorForSquareArea(QueryOperator operatorForSquareArea) {
        this.operatorForSquareArea = operatorForSquareArea;
    }

    public QueryOperator getOperatorForConstructionYear() {
        return operatorForConstructionYear;
    }

    public void setOperatorForConstructionYear(QueryOperator operatorForConstructionYear) {
        this.operatorForConstructionYear = operatorForConstructionYear;
    }

    @Override
    public String toString() {
        return "MittausSearchParameters{" +
                "searchKeyword='" + searchKeyword + '\'' +
                ", squareArea=" + squareArea +
                ", constructionYear=" + constructionYear +
                ", operatorForSquareArea=" + operatorForSquareArea +
                ", operatorForConstructionYear=" + operatorForConstructionYear +
                '}';
    }
}
