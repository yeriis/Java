public class Main {
    public static void main(String[] args) throws Exception{
        Model model = new Model();
        View view = new View(model);
        new Controller(model, view);
    }
}