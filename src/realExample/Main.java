package realExample;

import realExample.dao.ManufacturerDao;
import realExample.dao.MobileDao;
import realExample.pojo.Manufacturer;
import realExample.pojo.Mobile;

public class Main {
    public static void main(String[] args) {
//        Mobile mobile = new Mobile();
//        MobileDao mobileDao = new MobileDao();
//        //mobileDao.addMobile(mobile);
//        //mobileDao.deleteMobileById(8);
//        mobile = mobileDao.read(7);
//        System.out.println(mobile);
//        mobile.setPrice(70000F);
//        mobileDao.update(mobile);
//        mobile = mobileDao.read(7);
//        System.out.println(mobile);

        Manufacturer manufacturer = new Manufacturer(
                null, "Nokia", "Finland");
        ManufacturerDao manufacturerDao = new ManufacturerDao();
//        manufacturerDao.create(manufacturer);
//        manufacturerDao.delete(8);
        manufacturer = manufacturerDao.read(4);
        System.out.println(manufacturer);
        manufacturer.setCountry("Korea");
        manufacturerDao.update(manufacturer);
    }
}
