package MenuSelect;

import javax.swing.JCheckBox;

class FoodCheck extends JCheckBox {
    private Food food;

    public FoodCheck(Food food) {
        // 체크박스를 food toString 형식으로 나오게 만듬
        super(food.toString());
        setFood(food);
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}