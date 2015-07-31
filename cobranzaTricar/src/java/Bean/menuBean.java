package Bean;

import Dao.MenuDao;
import Dao.MenuDaoImplements;
import Model.Menu;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author master
 */
@ManagedBean
@SessionScoped
public class menuBean {
    
    public Menu menu = new Menu();
    public List<Menu> menus;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getMenus() {
        MenuDao menudao = new MenuDaoImplements();
        menus = menudao.mostrarMenu();
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
    
    public menuBean() {
    }
    
    public void insertar (){
        MenuDao linkDao = new MenuDaoImplements();
        Date d = new Date();
        menu.setFechareg(d);
        linkDao.insertarMenu(menu);
        menu = new Menu();
        
    }
    
    public void modificar(){
        MenuDao linkDao = new MenuDaoImplements();
        linkDao.modificarMenu(menu);
        menu = new Menu();
    }
    
    public void eliminar(){
        MenuDao linkDao = new MenuDaoImplements();
        linkDao.eliminarMenu(menu);
        menu = new Menu();
    }
    
}
