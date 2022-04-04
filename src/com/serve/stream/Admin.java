package com.serve.stream;

import com.serve.assembly.ClanManager;
import com.serve.server.Manager;
import com.serve.server.ShinwaManager;
import com.serve.server.ThienDiaBangManager;

public class Admin implements Runnable{
    private int timeCount;
    private Server server;

    public Admin(int minues, Server server) {
        this.timeCount = minues;
        this.server = server;
    }

    public void run() {
        try {
            while (timeCount > 0) {
                Manager.serverChat("Thông báo bảo trì","Hệ thống sẽ bảo trì sau " + timeCount + " phút nữa. Vui lòng thoát game trước thời gian bảo trì, để tránh mất vật phẩm. Xin cảm ơn!");
                timeCount--;
                Thread.sleep(60000);
            }
            if(timeCount == 0) {
                ClanManager.close();
                ThienDiaBangManager.close();
                ShinwaManager.close();
                this.server.close(100L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
