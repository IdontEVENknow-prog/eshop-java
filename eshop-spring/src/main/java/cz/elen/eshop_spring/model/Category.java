package cz.elen.eshop_spring.model;

public class Category {

    private Long id;
    private String name;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }


}
