package poolMVC;

import product.DisplayablePool;

public class DisplayPool {
    public DisplayPool(DisplayablePool pool) {
        PoolModel model = new PoolModel(pool);
        PoolView view = new PoolView(model.getPool());
        PoolController controller = new PoolController(model, view);

        controller.readInput();
    }


}
