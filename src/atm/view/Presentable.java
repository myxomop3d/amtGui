package atm.view;

/**
 * Created by myxom on 24.09.2016.
 */
public interface Presentable {
    void showScreen(String msg, boolean clear);

    void showError(String msg, boolean clear);
}
