package com.serve.stream;

import com.serve.assembly.ClanManager;
import com.serve.assembly.Map;
import com.serve.assembly.Player;
import com.serve.io.Message;
import com.serve.io.Util;
import com.serve.server.Manager;
import com.serve.server.Service;
import com.serve.server.Session;
import com.serve.server.ThienDiaBangManager;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class RunTimeServer extends Thread{
    private static int[] hoursAutoSaveData = new int[] { 1, 5, 7, 11, 15, 19, 21, 23 };
    private static int[] hoursRefreshBoss = new int[] { 8, 12, 14, 20, 22 };
    private static int[] hoursRefreshBossVDMQ = new int[] { 1, 3, 9, 11, 13, 17, 19, 21, 23 };
    private static boolean[] isRefreshBoss = new boolean[] { false, false, false, false, false, false };
    private static boolean[] isRefreshBossVDMQ = new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false };
    private static short[] mapBossVDMQ = new short[] { 141, 142, 143 };
    private static short[] mapBoss45 = new short[] { 14, 15, 16, 34, 35, 52, 68 };
    private static short[] mapBoss55 = new short[] { 44, 67 };
    private static short[] mapBoss65 = new short[] { 24, 41, 45, 59 };
    private static short[] mapBoss75 = new short[] { 18, 36, 54 };
    private static final short[]mapBossSKTet = new short[]{2,28,39};
    private static final int[] hoursRefreshBossSKTet =  new int[]{1,3,5,7,9,10,13,15,17,19,22,23};
    private static final boolean[] isRefreshBossSKTet = new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false};

    @Override
    public void run() {
        try {
            ClanManager clan;
            int i;
            Calendar rightNow;
            int hour;
            int min;
            int sec;
            int j;
            byte k;
            Map map;
            Player player;
            while (Server.running) {
                synchronized (ClanManager.entrys) {
                    for (i = ClanManager.entrys.size() - 1; i >= 0; --i) {
                        if(ClanManager.entrys.get(i) != null) {
                            clan = ClanManager.entrys.get(i) ;
                            if (!Util.isSameWeek(Date.from(Instant.now()), Util.getDate(clan.week))) {
                                clan.payfeesClan();
                            }
                        }
                    }
                }

                synchronized (ThienDiaBangManager.thienDiaBangManager) {
                    if(ThienDiaBangManager.thienDiaBangManager[0] != null) {
                        if (!Util.isSameWeek(Date.from(Instant.now()), Util.getDate2(ThienDiaBangManager.thienDiaBangManager[0].getWeek()))) {
                            ThienDiaBangManager.register = false;
                            ThienDiaBangManager.resetThienDiaBang();
                        }
                    }
                }

                rightNow = Calendar.getInstance();
                hour = rightNow.get(11);
                min = rightNow.get(12);
                sec = rightNow.get(13);

                if(hour % 24 == 0 && min == 0 && sec == 0) {
                    if(ChienTruong.chienTruong != null) {
                        ChienTruong.chienTruong.finish();
                    }
                    ChienTruong.chienTruong30 = false;
                    ChienTruong.chienTruong50 = false;
                    ChienTruong.finish = false;
                    ChienTruong.start = false;
                    ChienTruong.pointHacGia = 0;
                    ChienTruong.pointBachGia = 0;
                    ChienTruong.pheWin = -1;
                    ChienTruong.bxhCT.clear();
                    ChienTruong.chienTruong = null;
                }

                if(hour % 23 == 0 && min >= 44) {
                    ThienDiaBangManager.register = false;
                }
                if(hour % 24 == 0 && min >= 5) {
                    ThienDiaBangManager.register = true;
                }

                if(ChienTruong.chienTruong != null) {
                    if(ChienTruong.bxhCT.size() > 0) {
                        ChienTruong.bxhCT = ChienTruong.sortBXH(ChienTruong.bxhCT);
                        Service.updateCT();
                    }
                }

          /*       if (sec %5 == 0) {
                    Manager.serverChat("B??? B??ch", "?????t ??c c???c");
                }
                  if (sec %5 == 0) {
                    Manager.serverChat("B??? B??ch", "?????t ngu m??i m??i ngu c?? d??ng code d??? vcl k vi???t dc");
                }*/
                if(hour == 19 && min == 00 && sec == 0) {
                    if(ChienTruong.chienTruong != null) {
                        ChienTruong.chienTruong.finish();
                    }
                    if(ChienTruong.chienTruong == null) {
                        Manager.serverChat("Server", "Chi???n tr?????ng lv30 ???? m??? b??o danh, h??y nhanh ch??n ?????n b??o danh chu???n b??? chi???n ?????u.");
                        ChienTruong.chienTruong30 = true;
                        ChienTruong.chienTruong50 = false;
                        ChienTruong.chienTruong = new ChienTruong();
                        ChienTruong.finish = false;
                        ChienTruong.start = false;
                        ChienTruong.pointHacGia = 0;
                        ChienTruong.pointBachGia = 0;
                        ChienTruong.pheWin = -1;
                        ChienTruong.bxhCT.clear();
                    }
                }

                if(ChienTruong.chienTruong != null && hour == 19 && min == 30 && sec == 0) {
                    ChienTruong.start = true;
                }

                if(ChienTruong.chienTruong != null && hour == 20 && min == 30 && sec == 0 && ChienTruong.start) {
                    ChienTruong.chienTruong.finish();
                }

                if(hour == 21 && min == 0 && sec == 0) {
                    if(ChienTruong.chienTruong != null) {
                        ChienTruong.chienTruong.finish();
                    }
                    if(ChienTruong.chienTruong == null) {
                        Manager.serverChat("Server", "Chi???n tr?????ng lv50 ???? m??? b??o danh, h??y nhanh ch??n ?????n b??o danh chu???n b??? chi???n ?????u.");
                        ChienTruong.chienTruong50 = true;
                        ChienTruong.chienTruong30 = false;
                        ChienTruong.chienTruong = new ChienTruong();
                        ChienTruong.finish = false;
                        ChienTruong.start = false;
                        ChienTruong.pointHacGia = 0;
                        ChienTruong.pointBachGia = 0;
                        ChienTruong.pheWin = -1;
                        ChienTruong.bxhCT.clear();
                    }
                }

                if(ChienTruong.chienTruong != null && hour == 21 && min == 30 && sec == 0) {
                    ChienTruong.start = true;
                }

                if(ChienTruong.chienTruong != null && hour == 22 && min == 30 && sec == 0 && ChienTruong.start) {
                    ChienTruong.chienTruong.finish();
                }
                
                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(44);//thay id npc
                        m.writer().writeUTF("Khi n??o c??c tr??m l??n VIP h??y ?????n g???p ta"); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(55);//thay id npc
                        m.writer().writeUTF("Ch??o anh em t??n l?? Bulma... N??m nay em v???a tr??n 18..."); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(6);//thay id npc
                        m.writer().writeUTF("T??i c?? th??? c?????ng l???i m???i th???... tr??? s??? c??m d???."); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(52);//thay id npc
                        m.writer().writeUTF("C??n c??i n???t"); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(53);//thay id npc
                        m.writer().writeUTF("C??n c??i n???t"); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(54);//thay id npc
                        m.writer().writeUTF("C??n c??i n???t"); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(50);//thay id npc
                        m.writer().writeUTF("C??n c??i n???t"); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(12);//thay id npc
                        m.writer().writeUTF("Ph??? n??? gi???ng nh?? con ???????ng, ???????ng c??ng cong th?? c??ng nguy hi???m."); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(12);//thay id npc
                        m.writer().writeUTF("Ch??o con"); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(4);//thay id npc
                        m.writer().writeUTF("Ti???n th?? em kh??ng thi???u nh??ng nhi???u th?? em kh??ng c??."); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(7);//thay id npc
                        m.writer().writeUTF("N???u ???? y??u th?? y??u cho t???i, ?????ng n???a v???i r???i l???i v???i bu??ng l??i."); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(8);//thay id npc
                        m.writer().writeUTF("Kh??? n??ng thi??n b???m c???a nh???ng ?????a th???n kinh l?? l??m cho nh???ng ?????a th??ng minh ph???i ???c ch???."); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(9);//thay id npc
                        m.writer().writeUTF("T????ng lai kh??c hay c?????i c??n ph??? thu???c v??o ????? l?????i c???a qu?? kh???."); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(10);//thay id npc
                        m.writer().writeUTF("Y??u l?? ph???i n??i, gi???ng nh?? ????i l?? ph???i ??n."); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(11);//thay id npc
                        m.writer().writeUTF(" H??nh nh?? t??i chi???u c??c em qu?? n??n c??c em h?? r???i ph???i kh??ng? Th??i, em th??ch l?? ???????c. Em l?? c???a anh."); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(36);//thay id npc
                        m.writer().writeUTF("?????i l?? b??? kh???, qua h???t b??? kh??? th?? h???t ?????i."); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(45);//thay id npc
                        m.writer().writeUTF("M??i b??n nhau b???n nh??!"); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(46);//thay id npc
                        m.writer().writeUTF("c??n c??i n???t b???n ??"); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

             /*   if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(44);//thay id npc
                        m.writer().writeUTF("Th??? m???t l???n ch??i l???n xem c?? ai tr???m tr??? kh??ng."); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/

                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(47);//thay id npc
                        m.writer().writeUTF("H??nh nh?? t??i chi???u c??c em qu?? n??n c??c em h?? r???i ph???i kh??ng? Th??i, em th??ch l?? ???????c. Em l?? c???a anh."); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(42);//thay id npc
                        m.writer().writeUTF("Ch??o em, anh ?????ng ????y t??? chi???u "); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(41);//thay id npc
                        m.writer().writeUTF("M??o m??o meo "); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (sec %5 == 0) {
                    Message m = null;
                    try {
                        m = new Message(38);
                        m.writer().writeShort(38);//thay id npc
                        m.writer().writeUTF("Nh?? t??i 3 ?????i b???p "); // thay n???i dung chat
                        m.writer().flush();
                        Client.gI().NinjaMessage(m);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                /*if(TuTien.tuTien == null && (hour == 21)) {
                    TuTien.start = true;
                    TuTien.tuTien100 = true;
                    TuTien.tuTien50 = false;
                    TuTien.tuTien = new TuTien();
                    TuTien.finish = false;
                    //System.err.println("Open Ti??n C???nh");
                    Manager.serverChat("Server", "C???i l??o ho??n ?????ng to??n server ???? m??? ae h??y v??o c??y cu???c");
                }

                if(TuTien.tuTien != null && (hour == 22 && min == 0 && sec == 0) && TuTien.start) {
                    Manager.serverChat("Server", "C???i l??o ho??n ?????ng to??n server ???? ????ng c???a h??y quay l???i v??o ng??y mai.");
                    TuTien.tuTien.finish();
                    System.err.println("Close Ti??n C???nh");
                }*/
                
                if(TuTien.tuTien == null && (hour % 2 == 0)) {
                    TuTien.start = true;
                    TuTien.tuTien50 = true;
                    TuTien.tuTien100 = false;
                    TuTien.tuTien = new TuTien();
                    TuTien.finish = false;
                    //System.err.println("Open Ti??n C???nh");
                    Manager.serverChat("Server", "C???i l??o ho??n ?????ng VIP ???? m??? ae h??y v??o c??y cu???c.");
                
                }

                if(TuTien.tuTien != null && (hour % 2 != 0 && min == 0 && sec == 0) && TuTien.start) {
                    Manager.serverChat("Server", "C???i l??o ho??n ?????ng VIP ???? m??? ae h??y v??o sau");
                    System.err.println("Close Ti??n C???nh");
                    TuTien.tuTien.finish();
                }
                
                if(sec == 10) {                                  
                    synchronized (Client.gI().conns) {
                        for (i = 0; i < Client.gI().conns.size(); ++i) {
                            Session conn = (Session) Client.gI().conns.get(i);
                            if (conn != null) {
                                player = conn.player;
                                if (player != null) {
                                    if (player.c == null) {
                                        Client.gI().kickSession(conn);
                                    }
                                } else if (player == null) {
                                    Client.gI().kickSession(conn);
                                }
                            }
                        }
                  //      System.out.println(" Clear clone login");
                    }                      
                }
                if((min == 58 || min == 30)&& sec == 0) {
                    Manager.serverChat("Server", "H??? th???ng ??ang t??? ?????ng c???p nh???t d??? li???u ng?????i ch??i c?? th??? g??y lag!");
                    SaveData saveData = new SaveData();
                    Thread t1 = new Thread(saveData);
                    t1.start();
                    if(!Manager.isSaveData) {
                        t1 = null;
                        saveData= null;
                    }
                }

                for(j = 0; j < this.hoursRefreshBossVDMQ.length; ++j) {
                    if (this.hoursRefreshBossVDMQ[j] == hour) {
                        if (!this.isRefreshBossVDMQ[j]) {
                            String textchat = "BOSS ???? xu???t hi???n t???i:";
                            for (k = 0; k < this.mapBossVDMQ.length; ++k) {
                                map = Manager.getMapid(this.mapBossVDMQ[k]);
                                if (map != null) {
                                    map.refreshBoss(Util.nextInt(15, 28));
                                    if(k==0) {
                                        textchat = textchat + " " + map.template.name;
                                    } else {
                                        textchat = textchat + ", " + map.template.name;
                                    }
                                    this.isRefreshBossVDMQ[j] = true;
                                }
                            }
                            Manager.chatKTG(textchat);
                        }
                    } else {
                        this.isRefreshBossVDMQ[j] = false;
                    }
                }
                for (j = 0; j < this.hoursRefreshBoss.length; ++j) {
                    if (this.hoursRefreshBoss[j] == hour) {
                        if (!this.isRefreshBoss[j]) {
                            String textchat = "Th???n th?? ???? xu???t hi???n t???i:";
                            for (k = 0; k < Util.nextInt(1, 2); ++k) {
                                map = Manager.getMapid(this.mapBoss75[Util.nextInt(this.mapBoss75.length)]);
                                if (map != null) {
                                    map.refreshBoss(Util.nextInt(15, 28));
                                    textchat = textchat + " " + map.template.name;
                                    this.isRefreshBoss[j] = true;
                                }
                            }
                            for (k = 0; k < Util.nextInt(1, 2); ++k) {
                                map = Manager.getMapid(this.mapBoss65[Util.nextInt(this.mapBoss65.length)]);
                                if (map != null) {
                                    map.refreshBoss(Util.nextInt(15, 28));
                                    textchat = textchat + ", " + map.template.name;
                                    this.isRefreshBoss[j] = true;
                                }
                            }
                            for (k = 0; k < Util.nextInt(1, 2); ++k) {
                                map = Manager.getMapid(this.mapBoss55[Util.nextInt(this.mapBoss55.length)]);
                                if (map != null) {
                                    map.refreshBoss(Util.nextInt(15, 28));
                                    textchat = textchat + ", " + map.template.name;
                                    this.isRefreshBoss[j] = true;
                                }
                            }
                            for (k = 0; k < Util.nextInt(1, 2); ++k) {
                                map = Manager.getMapid(this.mapBoss45[Util.nextInt(this.mapBoss45.length)]);
                                if (map != null) {
                                    map.refreshBoss(Util.nextInt(15, 28));
                                    textchat = textchat + ", " + map.template.name;
                                    this.isRefreshBoss[j] = true;
                                }
                            }
//                                    for (byte k = 0; k < Server.mapBossVDMQ.length; ++k) {
//                                        Map map = Manager.getMapid(Server.mapBossVDMQ[k]);
//                                        if (map != null) {
//                                            map.refreshBoss(util.nextInt(15, 30));
//                                            textchat = textchat + ", " + map.template.name;
//                                            Server.isRefreshBoss[j] = true;
//                                        }
//                                    }
                            Manager.chatKTG(textchat);
                        }
                    }
                    else {
                        this.isRefreshBoss[j] = false;
                    }
                }
                //th??ng b??o Boss T???t ra ...
                for (int i1 = 0; i1 < hoursRefreshBossSKTet.length; i1++) {
                        if (hoursRefreshBossSKTet[i1] == hour) {
                            if (!isRefreshBossSKTet[i1]) {
                                String textchat = "Boss Chu???t Canh T?? ???? xu???t hi???n t???i";
                                for (byte j1 = 0; j1 < mapBossSKTet.length; j1++) {
                                    map = Manager.getMapid(mapBossSKTet[j1]);
                                    if (map != null) {
                                        int khu = Util.nextInt(15,30);
                                        map.refreshBossTet(khu);
                                        System.out.println("khu" + khu);
                                        textchat += ", "+map.template.name;
                                        this.isRefreshBossSKTet[i1] = true;
                                        
                                    }
                                }
                                Manager.chatKTG(textchat);
                            }
                        } else {
                            isRefreshBossSKTet[i1] = false;
                        }
                          
                    }
                Thread.sleep(1000L);
            }
            return;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
