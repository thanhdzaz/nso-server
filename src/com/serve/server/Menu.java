package com.serve.server;

import com.serve.assembly.*;
import com.serve.io.Message;
import com.serve.io.SQLManager;
import com.serve.io.Util;
import com.serve.stream.*;
import com.serve.stream.thiendiabang.ThienDiaData;
import com.serve.template.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.serve.assembly.CheckCLCoin;
import com.serve.assembly.CheckCLLuong;
import com.serve.assembly.CheckTXCoin;
import com.serve.assembly.CheckTXLuong;
import com.serve.assembly.CheckTXXu;

public class Menu {
    public void sendMessMenuNhiemVu(Player p, byte npcid, byte menuId, String str) throws IOException {
        NpcTemplate npc = (NpcTemplate)Manager.npcs.get(npcid);
        Message mss = new Message(39);
        DataOutputStream ds = mss.writer();
        ds.writeShort(npcid);
        ds.writeUTF(str);
        ds.writeByte(npc.menu[menuId].length);

        for(int i = 1; i < npc.menu[menuId].length; ++i) {
            ds.writeUTF(npc.menu[menuId][i]);
        }

        ds.flush();
        p.conn.sendMessage(mss);
        mss.cleanup();
    }
    
    public void test(){
        System.out.println("a");
    }

    public static void doMenuArray(Player p, String[] menu) {
        Message m = null;
        try {
            m = new Message(63);
            for(byte i = 0; i < menu.length; ++i) {
                m.writer().writeUTF(menu[i]);
            }
            m.writer().flush();
            p.conn.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }

    }

    public static void sendWrite(Player p, short type, String title) {
        Message m = null;
        try {
            m = new Message(92);
            m.writer().writeUTF(title);
            m.writer().writeShort(type);
            m.writer().flush();
            p.conn.sendMessage(m);
            m.cleanup();
        } catch (IOException var5) {
            var5.printStackTrace();
        } finally {
            if(m != null) {
                m.cleanup();
            }
        }

    }

    public static void menuId(Player p, Message ms) {
        try {
            short npcId = ms.reader().readShort();
            ms.cleanup();
            p.c.typemenu = 0;
            p.typemenu = npcId;
            if (npcId == 33) {
                switch(Server.manager.event) {
                    case 1: {
                        Menu.doMenuArray(p, new String[]{"Di???u gi???y", "Di???u v???i"});
                        break;
                    }
                    case 2: {
                        Menu.doMenuArray(p, new String[]{"H???p b??nh th?????ng", "H???p b??nh th?????ng h???ng", "B??nh th???p c???m", "B??nh d???o", "B??nh ?????u xanh", "B??nh p??a"});
                        break;
                    }
                    case 3: {
                        Menu.doMenuArray(p, new String[]{"B??nh Chocolate", "B??nh d??u t??y", "?????i m???t n???", "?????i pet","BXH Di???t Boss TL", "H?????ng d???n"});
                        break;
                    }
                    case 4: {
                        Menu.doMenuArray(p, new String[]{"B??nh Ch??ng","B??nh T??t","L?? x??","L??m Ph??o","Top di???t chu???t", "H?????ng d???n"});
                        break;
                    }
                    case 5: {
                        Menu.doMenuArray(p, new String[]{"L??m Hoa H???ng ?????","L??m Hoa H???ng V??ng","L??m Hoa H???ng Xanh","L??m Gi??? Hoa","T???ng Hoa H???ng ?????","T???ng Hoa H???ng V??ng","T???ng Hoa H???ng Xanh","T???ng Gi??? Hoa","K???t Hoa"});
                    }
                    default: {
                        break;
                    }
                }
            }
            else if(npcId == 40) {
                switch (p.c.mapid) {
                    case 117: {
                        Menu.doMenuArray(p, new String[]{"R???i kh???i n??i n??y", "?????t c?????c", "H?????ng d???n"});
                        break;
                    }
                    case 118:
                    case 119: {
                        Menu.doMenuArray(p, new String[]{"R???i kh???i n??i n??y", "Th??ng tin"});
                        break;
                    }
                }
            }

             ms = new Message((byte)40);
            ms.writer().flush();
            p.conn.sendMessage(ms);
            ms.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ms != null) {
                ms.cleanup();
            }
        }
    }

    public static void menu(Player p, Message ms) {
        try {
            byte npcId = ms.reader().readByte();
            byte menuId = ms.reader().readByte();
            byte b3 = ms.reader().readByte();
            if (ms.reader().available() > 0) {
                byte var6 = ms.reader().readByte();
            }
            ms.cleanup();
            if ((p.typemenu == -1 || p.typemenu == 0) && p.typemenu != npcId) {
                switch(npcId) {
                    case 0:
                        Menu.npcKanata(p, npcId, menuId, b3);
                        break;
                    case 1:
                        Menu.npcFuroya(p, npcId, menuId, b3);
                        break;
                    case 2:
                        Menu.npcAmeji(p, npcId, menuId, b3);
                        break;
                    case 3:
                        Menu.npcKiriko(p, npcId, menuId, b3);
                        break;
                    case 4:
                        Menu.npcTabemono(p, npcId, menuId, b3);
                        break;
                    case 5:
                        Menu.npcKamakura(p, npcId, menuId, b3);
                        break;
                    case 6:
                        Menu.npcKenshinto(p, npcId, menuId, b3);
                        break;
                    case 7:
                        Menu.npcUmayaki_Lang(p, npcId, menuId, b3);
                        break;
                    case 8:
                        Menu.npcUmayaki_Truong(p, npcId, menuId, b3);
                        break;
                    case 9:
                        Menu.npcToyotomi(p, npcId, menuId, b3);
                        break;
                    case 10:
                        Menu.npcOokamesama(p, npcId, menuId, b3);
                        break;
                    case 11:
                        Menu.npcKazeto(p, npcId, menuId, b3);
                        break;
                    case 12:
                        Menu.npcTajima(p, npcId, menuId, b3);
                        break;
                    case 18:
                        Menu.npcRei(p, npcId, menuId, b3);
                        break;
                    case 19:
                        Menu.npcKirin(p, npcId, menuId, b3);
                        break;
                    case 20:
                        Menu.npcSoba(p, npcId, menuId, b3);
                        break;
                    case 21:
                        Menu.npcSunoo(p, npcId, menuId, b3);
                        break;
                    case 22:
                        Menu.npcGuriin(p, npcId, menuId, b3);
                        break;
                    case 23:
                        Menu.npcMatsurugi(p, npcId, menuId, b3);
                        break;
                    case 24:
                        Menu.npcOkanechan(p, npcId, menuId, b3);
                        break;
                    case 25:
                        Menu.npcRikudou(p, npcId, menuId, b3);
                        break;
                    case 26:
                        Menu.npcGoosho(p, npcId, menuId, b3);
                        break;
                    case 27:
                        Menu.npcTruCoQuan(p, npcId, menuId, b3);
                        break;
                    case 28:
                        Menu.npcShinwa(p, npcId, menuId, b3);
                        break;
                    case 29:
                        Menu.npcChiHang(p, npcId, menuId, b3);
                        break;
                    case 30:
                        Menu.npcRakkii(p, npcId, menuId, b3);
                        break;
                    case 31:
                        Menu.npcLongDen(p, npcId, menuId, b3);
                        break;
                    case 32:
                        Menu.npcKagai(p, npcId, menuId, b3);
                        break;
                    case 33:
                        Menu.npcTienNu(p, npcId, menuId, b3);
                        break;
                    case 34:
                        Menu.npcCayThong(p, npcId, menuId, b3);
                        break;
                    case 35:
                        Menu.npcOngGiaNoen(p, npcId, menuId, b3);
                        break;
                    case 36:
                        Menu.npcVuaHung(p, npcId, menuId, b3);
                        break;
                    case 37:
                        Menu.npcKanata_LoiDai(p, npcId, menuId, b3);
                        break;                       
                    case 38:
                        Menu.npcAdmin(p, npcId, menuId, b3);
                        break;
                    case 39: {
                        Menu.npcRikudou_ChienTruong(p, npcId, menuId, b3);
                        break;
                    }
                     case 42:{ 
                        Menu.npccasino(p, npcId, menuId, b3);
                        break;
                    }
                    case 43:{ 
                        Menu.npcHoadao(p, npcId, menuId, b3);
                        break;
                    }
                    case 40: {
                        Menu.npcKagai_GTC(p, npcId, menuId, b3);
                        break;
                    }
                    case 44: {
                        Menu.npcVip(p, npcId, menuId, b3);
                        break;
                    }
                    case 45: {
                        Menu.npcMiNuong(p, npcId, menuId, b3);
                        break;
                    }
                    case 46: {
                        Menu.npcCLXTCoin(p, npcId, menuId, b3);
                        break;
                    }
                    case 47: {
                        Menu.npcSuKien(p, npcId, menuId, b3);
                        break;
                    }
                    case 48: {
                        Menu.npcPhoBan(p, npcId, menuId, b3);
                        break;
                    }
                    case 50: {
                        Menu.npcNangCap(p, npcId, menuId, b3);
                        break;
                    }
                    case 54: {
                        Menu.npcThanMeo(p, npcId, menuId, b3);
                        break;
                    }
                    case 55: {
                        Menu.npcBulma(p, npcId, menuId, b3);
                        break;
                    }
                   /*case 44: {
                        Menu.npcCLXTCoin(p, npcId, menuId, b3);
                        break;
                    }
                    
                    case 46: {
                        Menu.npcCLXTXu(p, npcId, menuId, b3);
                        break;
                    }
                    case 41: {
                        Menu.HUYDAT(p, npcId, menuId, b3);
                        break;
                    }*/
                    case 92:
                        p.typemenu = menuId == 0 ? 93 : 94;
                        Menu.doMenuArray(p, new String[]{"Th??ng tin", "Lu???t ch??i"});
                        break;
                    case 93:
                        if (menuId == 0) {
                            Server.manager.rotationluck[0].luckMessage(p);
                        } else if (menuId == 1) {
                            Server.manager.sendTB(p, "V??ng xoay vip", "Tham gia ??i, xem lu???t l??m g??");
                        }
                        break;
                    case 94:
                        if (menuId == 0) {
                            Server.manager.rotationluck[1].luckMessage(p);
                        } else if (menuId == 1) {
                            Server.manager.sendTB(p, "V??ng xoay th?????ng", "Tham gia ??i xem lu???t lm g??");
                        }
                    case 95:
                        break;
                    case 120: {
                        if (menuId > 0 && menuId < 7) {
                            Admission.Admission(p,menuId);
                        }
                    }
                    default: {
                        Service.chatNPC(p, (short) npcId, "Ch???c n??ng n??y ??ang ???????c c???p nh???t");
                        break;
                    }
                }
            }
            else if (p.typemenu == npcId) {
                switch(p.typemenu) {
                    case 0:
                        Menu.npcKanata(p, npcId, menuId, b3);
                        break;
                    case 1:
                        Menu.npcFuroya(p, npcId, menuId, b3);
                        break;
                    case 2:
                        Menu.npcAmeji(p, npcId, menuId, b3);
                        break;
                    case 3:
                        Menu.npcKiriko(p, npcId, menuId, b3);
                        break;
                    case 4:
                        Menu.npcTabemono(p, npcId, menuId, b3);
                        break;
                    case 5:
                        Menu.npcKamakura(p, npcId, menuId, b3);
                        break;
                    case 6:
                        Menu.npcKenshinto(p, npcId, menuId, b3);
                        break;
                    case 7:
                        Menu.npcUmayaki_Lang(p, npcId, menuId, b3);
                        break;
                    case 8:
                        Menu.npcUmayaki_Truong(p, npcId, menuId, b3);
                        break;
                    case 9:
                        Menu.npcToyotomi(p, npcId, menuId, b3);
                        break;
                    case 10:
                        Menu.npcOokamesama(p, npcId, menuId, b3);
                        break;
                    case 11:
                        Menu.npcKazeto(p, npcId, menuId, b3);
                        break;
                    case 12:
                        Menu.npcTajima(p, npcId, menuId, b3);
                        break;
                    case 18:
                        Menu.npcRei(p, npcId, menuId, b3);
                        break;
                    case 19:
                        Menu.npcKirin(p, npcId, menuId, b3);
                        break;
                    case 20:
                        Menu.npcSoba(p, npcId, menuId, b3);
                        break;
                    case 21:
                        Menu.npcSunoo(p, npcId, menuId, b3);
                        break;
                    case 22:
                        Menu.npcGuriin(p, npcId, menuId, b3);
                        break;
                    case 23:
                        Menu.npcMatsurugi(p, npcId, menuId, b3);
                        break;
                    case 24:
                        Menu.npcOkanechan(p, npcId, menuId, b3);
                        break;
                    case 25:
                        Menu.npcRikudou(p, npcId, menuId, b3);
                        break;
                    case 26:
                        Menu.npcGoosho(p, npcId, menuId, b3);
                        break;
                    case 27:
                        Menu.npcTruCoQuan(p, npcId, menuId, b3);
                        break;
                    case 28:
                        Menu.npcShinwa(p, npcId, menuId, b3);
                        break;
                    case 29:
                        Menu.npcChiHang(p, npcId, menuId, b3);
                        break;
                    case 30:
                        Menu.npcRakkii(p, npcId, menuId, b3);
                        break;
                    case 31:
                        Menu.npcLongDen(p, npcId, menuId, b3);
                        break;
                    case 32:
                        Menu.npcKagai(p, npcId, menuId, b3);
                        break;
                    case 33:
                        Menu.npcTienNu(p, npcId, menuId, b3);
                        break;
                    case 34:
                        Menu.npcCayThong(p, npcId, menuId, b3);
                        break;
                    case 35:
                        Menu.npcOngGiaNoen(p, npcId, menuId, b3);
                        break;
                    case 36:
                        Menu.npcVuaHung(p, npcId, menuId, b3);
                        break;                  
                    case 37:
                        Menu.npcKanata_LoiDai(p, npcId, menuId, b3);
                        break;
                    case 38:
                        Menu.npcAdmin(p, npcId, menuId, b3);
                        break;
                    case 39: {
                        Menu.npcRikudou_ChienTruong(p, npcId, menuId, b3);
                        break;
                    }
                    case 44: {
                        Menu.npcVip(p, npcId, menuId, b3);
                        break;
                    }
                    case 45: {
                        Menu.npcMiNuong(p, npcId, menuId, b3);
                        break;
                    }
                    case 46: {
                        Menu.npcCLXTCoin(p, npcId, menuId, b3);
                        break;
                    }
                    case 47: {
                        Menu.npcSuKien(p, npcId, menuId, b3);
                        break;
                    }
                    case 48: {
                        Menu.npcPhoBan(p, npcId, menuId, b3);
                        break;
                    }
                    case 50: {
                        Menu.npcNangCap(p, npcId, menuId, b3);
                        break;
                    }
                    case 54: {
                        Menu.npcThanMeo(p, npcId, menuId, b3);
                        break;
                    }
                    case 55: {
                        Menu.npcBulma(p, npcId, menuId, b3);
                        break;
                    }
                    /*case 40: {
                        Menu.npcKagai_GTC(p, npcId, menuId, b3);
                        break;
                    }
                    case 44: {
                        Menu.npcCLXTCoin(p, npcId, menuId, b3);
                        break;
                    }
                    case 45: {
                        Menu.npcCLXTLuong(p, npcId, menuId, b3);
                        break;
                    }
                    case 41: {
                        Menu.HUYDAT(p, npcId, menuId, b3);
                        break;
                    }*/
                    case 92:
                        p.typemenu = menuId == 0 ? 93 : 94;
                        doMenuArray(p, new String[]{"Th??ng tin", "Lu???t ch??i"});
                        break;
                    case 93:
                        if (menuId == 0) {

                            Server.manager.rotationluck[0].luckMessage(p);
                        } else if (menuId == 1) {

                            Server.manager.sendTB(p, "V??ng xoay vip", "Tham gia ??i, xem lu???t l??m g??");
                        }
                        break;
                    case 94:
                        if (menuId == 0) {
                            Server.manager.rotationluck[1].luckMessage(p);
                        } else if (menuId == 1) {
                            Server.manager.sendTB(p, "V??ng xoay th?????ng", "Tham gia ??i xem lu???t lm g??");
                        }
                    case 95:
                        break;
                    case 120: {
                        if (menuId > 0 && menuId < 7) {
                            Admission.Admission(p,(byte)menuId);
                        }
                    }
                    default: {
                        Service.chatNPC(p, (short) npcId, "Ch???c n??ng n??y ??ang ???????c c???p nh???t");
                        break;
                    }
                }
            }
            else {
                switch(p.typemenu) {
                    case -125: {
                        Menu.doiGiayVun(p, npcId, menuId, b3);
                        break;
                    }
                    case 92: {
                        switch (menuId) {
                            case 0: {
                                Server.manager.rotationluck[0].luckMessage(p);
                                break;
                            }
                            case 1: {
                                Server.manager.rotationluck[1].luckMessage(p);
                                break;
                            }
                        }
                        break;
                    }
                    //Send xu
                    case 125:
                        if (p.id != 1) {
                            Service.chatNPC(p, (short) npcId, "B???n Kh??ng C?? Quy???n");
                            break;
                        } else {
                            Service.sendInputDialog(p, (short) 55, "Nh???p IGAME ng?????i nh???n:");
                            break;
                        }
                    //Send L?????ng
                    case 126:  
                        if (p.id != 1) {
                            Service.chatNPC(p, (short) npcId, "B???n Kh??ng C?? Quy???n");
                            break;
                        } else {
                            Service.sendInputDialog(p, (short) 57, "Nh???p IGAME ng?????i nh???n:");
                            break;
                        }
                    //Send Y??n    
                    case 127: 
                        if (p.id != 1) {
                            Service.chatNPC(p, (short) npcId, "B???n Kh??ng C?? Quy???n");
                            break;
                        } else {
                            Service.sendInputDialog(p, (short) 59, "Nh???p IGAME ng?????i nh???n:");
                            break;
                        }
                    //Send Item
                    case 128:
                        if (p.id != 1) {
                            Service.chatNPC(p, (short) npcId, "B???n Kh??ng C?? Quy???n");
                            break;
                        } else {
                            Service.sendInputDialog(p, (short) 61, "Nh???p IGAME ng?????i nh???n:");
                            break;
                        }
                    //Send Mess
                    case 129:    
                        if (p.id != 1) {
                            Service.chatNPC(p, (short) npcId, "B???n Kh??ng C?? Quy???n");
                            break;
                        } else {
                            Service.sendInputDialog(p, (short) 64, "Nh???p IGAME ng?????i nh???n:");
                            break;
                        }
                    //M???nh top vk    
                    case 839:{
                        Menu.menuDoiVK(p, npcId, menuId, b3);
                        break;
                    }
                    case 9999: {
                        Menu.menuAdmin(p, npcId, menuId, b3);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
            p.typemenu = 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ms != null) {
                ms.cleanup();
            }
        }
    }
    
    public static void npcHoadao(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (menuId){
            case 0: {
                if (p.c.quantityItemyTotal(646) < 1){
                        Service.chatNPC(p, (short) npcid, "Con kh??ng c?? B??a May m???n ????? Xin L???c nh??");
                        return;
                       }
                else{
                    if(p.c.getBagNull() == 0){
                           p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng");
                           return;
                }   short[] arId = new short[]{12,12,12,8,9,8,9,275,276,277,278,275,276,277,278,548,12,548,381,382,381,382,381,682,682,682,228,227,226,225,224,223,222,283,436,438,437,436,437,419,403,419,403,407,407,12,254,255,256,12,254,255,256,7,8,9,436,437,438,682,383,382,381,222,223,224,225,226,227,228,251, 308,309,548,275,276,277,278,539,540,674,695,696,697,698,699,674,700,701,702,703,704,733,734,735,736,737,738,739,674,740,741,760,761,762,674,763,764,765,766,767,768,674,695,696,697,698,699,674,700,701,702,703,704,733,734,735,736,737,738,739,674,740,741,760,761,762,674,763,764,765,766,767,768,674,695,696,697,698,699,674,700,701,702,703,704,733,734,735,736,737,738,739,674,740,741,760,761,762,674,763,764,765,766,767,768,289,290,291,289,290,291,289,290,291};
                        short idI = arId[Util.nextInt(arId.length)];
                        Item itemup = ItemTemplate.itemDefault(idI);
                       itemup.isLock = false;
                       itemup.isExpires = true;
                       itemup.expires = Util.TimeDay(7);
                        p.c.removeItemBags(646, 1);
                       p.c.addItemBag(false, itemup);
                       p.sendAddchatYellow("B???n nh???n ???????c " + itemup);
                         p.updateExp(10000000L);
                }
                 break;
            }
            
            case 1: {
                Server.manager.sendTB(p,  "H?????ng D???n", "B???n c???n 1 B??a May M???n ????? Xin L???c ?????u Xu??n v?? s??? nh???n ???????c EXP v?? nh???ng m??n qu?? b???t ng???.");
                break;
            }              
        }       
    }

    public static void npcBulma(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                 if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                 if(p.c.level < 60) {
                    Service.chatNPC(p, (short) npcid, "Anh ch??a ????? c???p 60 ????? t???ng hoa cho iem\n Anh h??y tu luy???n th??m r???i ?????n g???p iem!\n Y??u........");   
                    break;
                }
                 if(p.c.quantityItemyTotal(862) < 1) {
                    Service.chatNPC(p, (short) npcid, "Anh kh??ng c?? hoa ????? t???ng t???ng hoa cho iem r???i\n D???i........");   
                    return;
                 }
                 if(p.c.getBagNull() < 1) {
                    p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng ????? nh???n qu??");
                    return;
                }
                p.c.diemtanghoa += 1;
                p.c.removeItemBag(p.c.getIndexBagid(862, false), 1);
                Item it;
                int per = Util.nextInt(300);
                if(per<1) {
                    it = ItemTemplate.itemDefault(862);
                } else {
                    per = Util.nextInt(UseItem.idTangHoa.length);
                    it = ItemTemplate.itemDefault(UseItem.idTangHoa[per]);
                }
                p.c.removeItemBag(p.c.getIndexBagid(862, true), 1);
                it.isLock = false;
                it.quantity = 1;
                p.updateExp(10000000L);
                p.c.addItemBag(true, it);
                Service.chatNPC(p, (short) npcid, "C???m ??n Anhh...Y??u"); 
                break;
        }
            case 1: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if(p.c.diemtanghoa < 5000) {
                    Service.chatNPC(p, (short) npcid, "Anh ch??a ????? 5000 ??i???m t???ng hoa ????? nh???n ????? x???n\n H??y t???ng em ????? 5000 b?? hoa h???ng\n Y??u");   
                    break;
                }
                if(p.c.getBagNull() < 3) {
                    p.conn.sendMessageLog("H??nh trang kh??ng ????? 3  ch??? tr???ng ????? nh???n qu??");
                    return;
                }
                if(p.c.diemtanghoa >= 5000) {
                    p.c.addItemBag(false, ItemTemplate.itemDefault(857));
                    p.c.addItemBag(false, ItemTemplate.itemDefault(858));
                    p.c.addItemBag(false, ItemTemplate.itemDefault(859));
                    Service.chatNPC(p, (short) npcid, "Anh ???? ?????i m??n qu?? 5000 ??i???m\n Y??u");   
                    p.c.diemtanghoa -= 5000;
                    break;
                }
           }
            case 2: {
               // Service.chatNPC(p, (short) npcid, "B???n ??ang c??: " + p.c.diemtanghoa + " ??i???m");   
                p.conn.sendMessageLog("??i???m t???ng hoa: " + p.c.diemtanghoa);
                break;
            }
            case 3: {
                Server.manager.sendTB(p, "H?????ng d???n", "Anh c???n t???ng hoa cho Bulma ho???c Nami"
                        + "\nM???i 1 b?? hoa t???ng s??? nh???n ???????c 1 ??i???m t???ng hoa\n"
                        + "C?? 2 c??ch xem ??i???m 1 l?? chat diemtanghoa, 2 l?? xem t???i npc Bulma ho???c Nami\n"
                        + "Hoa ???????c b??n t???i npc Gooso\n"
                        + "Khi ????? 5000 ??i???m s??? nh???n dc 1 set c???i trang Bulma ch??? s??? x???n\n"
                        + "Ch??c Anh S???m Tr??? Th??nh Tr??m VIP!");
                break;
            }
        }
    }
    
    public static void menuAdmin(Player p, byte npcid, byte menuId, byte b3) {
        Player player;
        int i;
        switch(menuId) {
            case 0: {
                Service.sendInputDialog(p, (short) 9998, "Nh???p s??? ph??t mu???n b???o tr?? 0->10 (0: ngay l???p t???c)");
                break;
            }
            case 1: {
                Service.KhoaTaiKhoan(p);
                break;
            }
            case 2: {
                Service.AutoSaveData();
                p.sendAddchatYellow("Update th??nh c??ng");
                break;
            }
            case 3: {
                String chat = "MapID: " + p.c.mapid + " - X: " + p.c.get().x + " - Y: " + p.c.get().y;
                p.conn.sendMessageLog(chat);
                break;
            }
            case 4: {
                Service.sendInputDialog(p, (short) 9996, "Nh???p s??? xu mu???n c???ng (c?? th??? nh???p s??? ??m)");
                break;
            }
            case 5: {
                Service.sendInputDialog(p, (short) 9995, "Nh???p s??? l?????ng mu???n c???ng (c?? th??? nh???p s??? ??m)");
                break;
            }
            case 6: {
                Service.sendInputDialog(p, (short) 9997, "Nh???p s??? y??n mu???n c???ng (c?? th??? nh???p s??? ??m)");
                break;
            }
            case 7: {
                Service.sendInputDialog(p, (short) 9994, "Nh???p s??? level mu???n t??ng (c?? th??? nh???p s??? ??m)");
                break;
            }
            case 8: {
                Service.sendInputDialog(p, (short) 9993, "Nh???p s??? ti???m n??ng mu???n t??ng (c?? th??? nh???p s??? ??m)");
                break;
            }
            case 9: {
                Service.sendInputDialog(p, (short) 9992, "Nh???p s??? k??? n??ng mu???n t??ng (c?? th??? nh???p s??? ??m)");
                break;
            }
            case 10: {
                SaveData saveData = new SaveData();
                saveData.player = p;
                Thread t1 = new Thread(saveData);
                t1.start();
                if (!Manager.isSaveData) {
                    player = null;
                    t1 = null;
                    saveData = null;
                }
                break;
            }
            case 11: {
                Service.sendInputDialog(p, (short) 9991, "Nh???p n???i dung");
                break;
            }
            case 12: {
                try {
                    Server.manager.sendTB(p, "Ki???m tra", "- T???ng s??? k???t n???i: " + Client.gI().conns_size() + "\n\n- S??? ng?????i ????ng nh???p: " + Client.gI().players_size() + "\n\n- T???NG S??? NG?????I CH??I TH???C T???: " + Client.gI().ninja_size());
                } catch (Exception var11) {
                    var11.printStackTrace();
                }
                break;
            }
            case 13: {
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
                }

                p.conn.sendMessageLog("D???n clone th??nh c??ng!");
                break;
            }
            case 14: {
                synchronized (Client.gI().conns) {
                    for (i = 0; i < Client.gI().conns.size(); ++i) {
                        player = ((Session) Client.gI().conns.get(i)).player;
                        if (player != null && player != p) {
                            Client.gI().kickSession(player.conn);
                        }
                    }
                }
                p.conn.sendMessageLog("D???n Session th??nh c??ng!");
                break;
            }
            case 15: {
                Service.sendInputDialog(p, (short) 9990, "Nh???p gi?? tr??? c???n thay ?????i");
                break;
            }
            case 16: {
                try {
                    String a = "";
                    int i2 = 1;
                    for (CheckRHB check: CheckRHB.checkRHBArrayList) {
                        a += i2 + ". " + check.name + " - " + check.item + " - " + check.time +".\n";
                        i2++;
                    }
                    Server.manager.sendTB(p, "Check RHB", a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 17: {
               try {
                   ResultSet red = SQLManager.stat.executeQuery("SELECT * FROM `alert` WHERE `id` = 1;");
                   if (red != null && red.first()) {
                       String alert = red.getString("content");
                       Manager.alert.setAlert(alert);
                       red.close();
                   }
                   p.sendAddchatYellow("C???p nh???t th??ng b??o th??nh c??ng");
                   Manager.alert.sendAlert(p);
               } catch (Exception e) {
                   p.conn.sendMessageLog("L???i c???p nh???t!");
               }
               break;
            }
            case 18: {
                try {
                    Manager.chatKTG("Ng?????i ch??i " + p.c.name + " s??? d???ng B??nh kh??c c??y d??u t??y ???? nh???n ???????c " + ItemTemplate.ItemTemplateId(385).name);
                } catch (Exception e) {
                    p.conn.sendMessageLog("L???i c???p nh???t!");
                }
                break;
            }
            case 19: {
                try {
                    Manager.chatKTG("Ng?????i ch??i " + p.c.name + " s??? d???ng B??nh kh??c c??y d??u t??y ???? nh???n ???????c " + ItemTemplate.ItemTemplateId(384).name);
                } catch (Exception e) {
                    p.conn.sendMessageLog("L???i c???p nh???t!");
                }
                break;
                } 
           case 20: {
                Service.sendInputDialog(p, (short) 9989, "Nh???p gi?? tr??? c???n thay ?????i");
                break;
           }
            case 21: {
                Service.sendInputDialog(p, (short) 41_0, "Nh???p t??n nh??n v???t :");
                break;
            }
            case 22: {
                Service.sendInputDialog(p, (short) 41_1, "Nh???p t??n nh??n v???t :");
                break;
            }
        }

    }

    public static void doiGiayVun(Player p, byte npcid, byte menuId, byte b3) {
        switch(menuId) {
            case 0: {
                p.c.removeItemBag(p.c.getIndexBagid(251, false), 250);
                p.c.addItemBag(false, ItemTemplate.itemDefault(252, false));
                break;
            }
            case 1: {
                p.c.removeItemBag(p.c.getIndexBagid(251, false), 300);
                p.c.addItemBag(false, ItemTemplate.itemDefault(253, false));
                break;
            }
        }

    }
    public static void npcThanMeo(Player p, byte npcid, byte menuId, byte b3) {
        int[] bk = {0, 397, 398, 399, 400, 401, 402};
        switch(menuId) {
            case 0: {
                if (p.c.isNhanban) {
                             p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if(p.c.getBagNull()< 1){
                            p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng");
                            return;
                        }
                        if (p.luong < 30000) {
                            p.conn.sendMessageLog("H??nh trang kh??ng ????? 30000 l?????ng");
                            return;
                        }
                        if (p.c.get().nclass == 0){
                            Service.chatNPC(p, (short)npcid, "Tr??m ch??a nh???p h???c ????? nh???n b?? k??p");
                            return;
                        }
                        if (p.c.get().nclass > 0){
                            Item itemup = ItemTemplate.itemDefault(bk[p.c.get().nclass]);
                            Option op = new Option(6, Util.nextInt(1, 10000));
                            itemup.options.add(op);
                            op = new Option(73, Util.nextInt(1, 6000));
                            itemup.options.add(op);
                            op = new Option(69, Util.nextInt(1, 50));
                            itemup.options.add(op);
                            op = new Option(81, Util.nextInt(1, 500));
                            itemup.options.add(op);
                            op = new Option(119, Util.nextInt(1, 5000));
                            itemup.options.add(op);
                            op = new Option(120, Util.nextInt(1, 5000));
                            itemup.options.add(op);
                            op = new Option(58, Util.nextInt(1, 25));
                            itemup.options.add(op);
                            op = new Option(68, Util.nextInt(1, 500));
                            itemup.options.add(op);

                            itemup.sys = p.c.getSys();
                      
                            p.c.addItemBag(false, itemup);
                            p.upluongMessage(-30000);
                            break;
                        }
                }
            case 1: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                Item itemup = p.c.ItemBody[15];
                if((itemup == null)){
                    p.conn.sendMessageLog("B???n ch??a mang b?? k??p");
                    return;
                }
                if(p.c.quantityItemyTotal(530) < 1000){
                    p.conn.sendMessageLog("B???n kh??ng c?? 1000 linh th???ch");
                    return;
                }
                if(itemup.upgrade == 16) {
                    p.conn.sendMessageLog("B?? K??p ???? ?????t c???p ????? t???i ??a");
                    return;
                }
                int[] xu = new int[16];
                xu[0] = 10000000;
                for(byte i = 1; i < 16; i++) {
                    xu[i] = xu[i - 1] + 10000000;
                }
                if(p.c.xu < xu[itemup.upgrade]){
                    p.conn.sendMessageLog("B???n kh??ng ????? " + xu[itemup.upgrade] + " xu ????? n??ng c???p b?? k??p");
                    return;
                }
                p.c.upxuMessage(-(xu[itemup.upgrade]));
                p.c.removeItemBody((byte)15);
                for (int i = 530; i <=530 ; i++) {
                    if (p.c.getIndexBagid(i, false) != -1000) {
                        p.c.removeItemBag(p.c.getIndexBagid(i, false), 1000);
                    } else {
                        p.c.removeItemBag(p.c.getIndexBagid(i, true), 1000);
                    }
                }
                itemup.ncBK((byte)1);
                p.c.addItemBag(false, itemup);
                break;
            }
            case 2: {
                Server.manager.sendTB(p, "B?? K??p", ">B?? k??p max ch??? s???<\n"
                        + "Hp t???i ??a: 1 ?????n 10000\n"
                        + "T???n c??ng: 1 ?????n 6000\n"
                        + "Ch?? m???ng: 1 ?????n 50\n"
                        + "Kh??ng t???t c???: 1 ?????n 500\n"
                        + "H???i hp, mp m???i 5 gi??y: 1 ?????n 5000\n"
                        + "C???ng th??m ti???m n??ng: 1 ?????n 25%\n"
                        + "N?? ????n: 1 ?????n 500\n"
                        + ">????? n??ng c???p c???n<\n"
                        + "Xu v?? Linh th???ch\n"
                        + "Linh th???ch ki???m ???????c t???i l??ng c???");
                break;
            }
            }
        }  
    
    //?????i vk top
    public static void menuDoiVK(Player p, byte npcid, byte menuId, byte b3) {
        int[] ids = {0, 632, 633, 634, 635, 636, 637};
        switch(menuId) {         
            case 0: {
                if(p.c.get().nclass == 0){
                    p.conn.sendMessageLog("B???n c???n nh???p h???c ????? s??? d???ng");
                    return;
                }
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if(p.c.getBagNull()< 1){
                    p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng");
                    return;
                }
                if(p.c.quantityItemyTotal(839) < 300){
                    p.conn.sendMessageLog("B???n kh??ng c?? 300 m???nh Th???n Binh");
                    return;
                }
                Item itemup = ItemTemplate.itemDefault(ids[p.c.get().nclass]);
                itemup.NhanVKTop(300);
                itemup.sys = p.c.getSys();
                itemup.upgradeNext((byte)16);
                p.c.addItemBag(false, itemup);
                p.c.removeItemBags(839, 300);
                break;
            }
            case 1: {
               if(p.c.get().nclass == 0){
                    p.conn.sendMessageLog("B???n c???n nh???p h???c ????? s??? d???ng");
                    return;
                }
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if(p.c.getBagNull()< 1){
                    p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng");
                    return;
                }
                if(p.c.quantityItemyTotal(839) < 500){
                    p.conn.sendMessageLog("B???n kh??ng c?? 500 m???nh Th???n Binh");
                    return;
                }
                Item itemup = ItemTemplate.itemDefault(ids[p.c.get().nclass]);
                itemup.NhanVKTop(500);
                p.c.addItemBag(false, itemup);
                itemup.sys = p.c.getSys();
                itemup.upgradeNext((byte)16);
                p.c.removeItemBags(839, 500);
                break;
            }
        }
    }
    
    // Tu ti??n
    /*public static void npcTuTien(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                if (p.c.level <50) {
                    p.conn.sendMessageLog("Tr??nh ????? ph???i ?????t level 50 tr??? l??n.");
                    return;
                }
                if (p.c.leveltutien >= 1) {
                    p.conn.sendMessageLog("Con ???? theo con ???????ng tu ti??n r???i m??.");
                    return;
                }
                if (p.luong <100000L) {
                    Service.chatNPC(p, (short)41, "????? theo con ???????ng tu ti??n con c???n n???p l??? ph?? 100k l?????ng.");
                    return;
                }
                p.upluongMessage(-100000L);
                p.c.leveltutien =1;
                Service.chatKTG(p.c.name + " ???? b???t ?????u theo con ???????ng tu ti??n.");
                break;
            }
            case 1: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if(TuTien.tuTien == null) {
                    Service.chatNPC(p, (short)npcid, "B??y gi??? ch??a ph???i th???i gian ????? tu luy???n.");
                    return;
                }
                if(TuTien.tuTien != null) {
                    if (TuTien.tuTien50 && (p.c.level < 50 || p.c.level >= 100)) {
                        Service.chatNPC(p, (short)npcid, "B??y gi??? l?? th???i gian tu luy???n c???a lv 50-99.");
                        return;
                    } else if (TuTien.tuTien100 && p.c.level < 100) {
                        Service.chatNPC(p, (short)npcid, "B??y gi??? l?? th???i gian tu luy???n c???a lv 100 tr??? l??n.");
                        return;
                    }
                }
                if (p.c.getEffId(34) == null) {
                    Service.chatNPC(p, (short)npcid, "Ph???i s??? d???ng th?? luy???n th??p m???i c?? th??? v??o.");
                    return;
                }
                Map ma = Manager.getMapid(TuTien.tuTien.map[0].id);
                    for (TileMap area : ma.area) {
                        if (area.numplayers < ma.template.maxplayers) {
                            p.c.tileMap.leave(p);
                            area.EnterMap0(p.c);
                            return;
                        }
                    }
                    break;
            }
            case 2: {
                String name;
                String options;
                    name = Server.manager.NameTuTien[p.c.leveltutien];
                    options = Server.manager.OptionsTuTien[p.c.leveltutien];
                    if (p.c.leveltutien <1) {
                        Server.manager.sendTB(p, "Th??ng tin", "Con ??ang l?? " + name + "\n" + options);
                    } else {
                        Server.manager.sendTB(p, "Th??ng tin", "Con ??ang tu luy???n ??? t???ng : " + name +"\n"+ options);
                    }
                break;
            }
            case 3: {
                Server.manager.sendTB(p, "H?????ng d???n","????? tu ti??n con c???n ?????t level 50 v?? c???n 100k l?????ng\n"
                        + "????? c?? exp tu ti??n ph???i tu luy???n trong map tu ti??n\n"
                        + "V??o 10h-15h l?? th???i gian cho c??c ti??n nh??n c???p ????? 50-99 tu luy???n\n"
                        + "V??o 17-22h l?? th???i gian cho c??c ti??n nh??n c???p ????? tr??n 100\n"
                        + "????? v??o ???????c map c???n c?? th?? luy???n th??p v?? c?? ch??? map nh?? trong l??ng c???.");
                return;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
    }*/
    
    public static void npcKanata(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                p.requestItem(2);
                break;
            }
            case 1: {
                switch (b3) {
                    case 0: {
                        if (!p.c.clan.clanName.isEmpty()) {
                            Service.chatNPC(p, (short) npcid, "Hi???n t???i con ???? c?? gia t???c, kh??ng th??? th??nh l???p gia t???c ???????c n???a.");
                            return;
                        }

                        if (p.luong < 100000) {
                            Service.chatNPC(p, (short) npcid, "????? th??nh l???p gia t???c, con ph???i c?? ??t nh???t 100000 l?????ng trong ng?????i.");
                            return;
                        }
                        Menu.sendWrite(p, (short) 50, "T??n gia t???c");
                        return;
                    }
                    case 1: {
                        if (p.c.clan.clanName.isEmpty()) {
                            Service.chatNPC(p, (short) npcid, "Hi???n t???i con ch??a c?? gia t???c, kh??ng th??? m??? L??nh ?????a gia t???c.");
                            return;
                        }

                        LanhDiaGiaToc lanhDiaGiaToc = null;
                        if (p.c.ldgtID != -1) {
                            if (LanhDiaGiaToc.ldgts.containsKey(p.c.ldgtID)) {
                                lanhDiaGiaToc = LanhDiaGiaToc.ldgts.get(p.c.ldgtID);
                                if (lanhDiaGiaToc != null && lanhDiaGiaToc.map[0] != null && lanhDiaGiaToc.map[0].area[0] != null) {
                                    if(lanhDiaGiaToc.ninjas.size() <= 24) {
                                        p.c.mapKanata = p.c.mapid;
                                        p.c.tileMap.leave(p);
                                        lanhDiaGiaToc.map[0].area[0].EnterMap0(p.c);
                                        return;
                                    } else {
                                        p.sendAddchatYellow("S??? th??nh vi??n tham gia L??nh ?????a Gia T???c ???? ?????t t???i ??a.");
                                    }
                                }
                            }
                        }
                        if(lanhDiaGiaToc == null){
                            if(p.c.clan.typeclan < 3) {
                                Service.chatNPC(p, (short) npcid, "Con kh??ng ph???i t???c tr?????ng ho???c t???c ph??, kh??ng th??? m??? L??nh ?????a gia t???c.");
                                return;
                            }
                            if(p.c.getBagNull() < 1) {
                                Service.chatNPC(p, (short) npcid, "H??nh trang c???a con kh??ng ????? ch??? tr???ng ????? nh???n Ch??a kho?? LDGT");
                                return;
                            }
                            ClanManager clan = ClanManager.getClanName(p.c.clan.clanName);
                            if (clan != null && p.c.clan.typeclan >= 3) {
                                if(clan.openDun <= 0) {
                                    Service.chatNPC(p, (short) npcid, "S??? l???n v??o LDGT tu???n n??y ???? h???t.");
                                    return;
                                }
                                if(clan.ldgtID != -1) {
                                    Service.chatNPC(p, (short) npcid, "L??nh ?????a gia t???c c???a con ??ang ???????c m??? r???i.");
                                    return;
                                }
                                clan.openDun--;
                                clan.flush();
                                lanhDiaGiaToc = new LanhDiaGiaToc();

                                Item itemup = ItemTemplate.itemDefault(260);
                                itemup.quantity = 1;
                                itemup.expires = System.currentTimeMillis() + 600000L;
                                itemup.isExpires = true;
                                itemup.isLock = true;
                                if(p.c.quantityItemyTotal(260) > 0) {
                                    p.c.removeItemBags(260, p.c.quantityItemyTotal(260));
                                }
                                p.c.addItemBag(false, itemup);
                                p.c.ldgtID = lanhDiaGiaToc.ldgtID;
                                clan.ldgtID = lanhDiaGiaToc.ldgtID;
                                lanhDiaGiaToc.clanManager = clan;
                                p.c.mapKanata = p.c.mapid;
                                p.c.tileMap.leave(p);
                                lanhDiaGiaToc.map[0].area[0].EnterMap0(p.c);
                                return;
                            }

                        }
                        break;
                    }
                    case 2: {
                        if (p.c.isNhanban) {
                            p.conn.sendMessageLog("Ch???c n??ng n??y kh??ng d??nh cho ph??n th??n");
                            return;
                        }
                        if(p.c.quantityItemyTotal(262) < 500) {
                            Service.chatNPC(p, (short) npcid, "Con c???n c?? 500 ?????ng ti???n gia t???c ????? ?????i l???y T??i qu?? gia t???c.");
                            return;
                        }
                        if(p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }
                        p.c.removeItemBags(262, 500);
                        Item itemup = ItemTemplate.itemDefault(263);
                        itemup.quantity = 1;
                        itemup.isLock = true;
                        p.c.addItemBag(true, itemup);
                        break;
                    }
                    case 3:
                    default: {
                        Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t");
                        break;
                    }
                }
                break;
            }
            case 2: {
                if (p.c.isNhanban) {
                    p.conn.sendMessageLog("Ch???c n??ng n??y kh??ng d??nh cho ph??n th??n");
                    return;
                }

                //Tr??? th?????ng
                if (b3 == 0) {
                    Service.evaluateCave(p.c);
                    return;
                }

                Cave cave = null;
                if (p.c.caveID != -1) {
                    if (Cave.caves.containsKey(p.c.caveID)) {
                        cave = Cave.caves.get(p.c.caveID);
                        if (cave != null && cave.map[0] != null && cave.map[0].area[0] != null) {
                            p.c.mapKanata = p.c.mapid;
                            p.c.tileMap.leave(p);
                            cave.map[0].area[0].EnterMap0(p.c);
                        }
                    }
                } else if (p.c.party != null && p.c.party.cave == null && p.c.party.charID != p.c.id) {
                    p.conn.sendMessageLog("Ch??? c?? nh??m tr?????ng m???i ???????c ph??p m??? c???a hang ?????ng");
                    return;
                }

                if (cave == null) {
                    if (p.c.nCave <= 0) {
                        Service.chatNPC(p, (short) npcid, "S??? l???n v??o hang ?????ng c???a con h??m nay ???? h???t, h??y quay l???i v??o ng??y mai.");
                        return;
                    }
                    if (b3 == 1) {
                        if (p.c.level < 30 || p.c.level > 39) {
                            p.conn.sendMessageLog("Tr??nh ????? kh??ng ph?? h???p");
                            return;
                        }
                        if (p.c.party != null) {
                            synchronized (p.c.party.aChar) {
                                for (byte i = 0; i < p.c.party.aChar.size(); ++i) {
                                    if (p.c.party.aChar.get(i).level < 30 || p.c.party.aChar.get(i).level > 39) {
                                        p.conn.sendMessageLog("Th??nh vi??n trong nh??m c?? tr??nh ????? kh??ng ph?? h???p");
                                        return;
                                    }
                                }
                            }
                        }
                        if (p.c.party != null) {
                            if (p.c.party.cave == null) {
                                cave = new Cave(3);
                                p.c.party.openCave(cave, p.c.name);
                            } else {
                                cave = p.c.party.cave;
                            }
                        } else {
                            cave = new Cave(3);
                        }
                        p.c.caveID = cave.caveID;
                        p.c.isHangDong6x = 1;
                    }
                    if (b3 == 2) {
                        if (p.c.level < 40 || p.c.level > 49) {
                            p.conn.sendMessageLog("Tr??nh ????? kh??ng ph?? h???p");
                            return;
                        }
                        if (p.c.party != null) {
                            synchronized (p.c.party) {
                                for (byte i = 0; i < p.c.party.aChar.size(); ++i) {
                                    if (p.c.party.aChar.get(i).level < 40 || p.c.party.aChar.get(i).level > 49) {
                                        p.conn.sendMessageLog("Th??nh vi??n trong nh??m c?? tr??nh ????? kh??ng ph?? h???p");
                                        return;
                                    }
                                }
                            }
                        }
                        if (p.c.party != null) {
                            if (p.c.party.cave == null) {
                                cave = new Cave(4);
                                p.c.party.openCave(cave, p.c.name);
                            } else {
                                cave = p.c.party.cave;
                            }
                        } else {
                            cave = new Cave(4);
                        }
                        p.c.caveID = cave.caveID;
                        p.c.isHangDong6x = 0;
                    }
                    if (b3 == 3) {
                        if (p.c.level < 50 || p.c.level > 59) {
                            p.conn.sendMessageLog("Tr??nh ????? kh??ng ph?? h???p");
                            return;
                        }
                        if (p.c.party != null) {
                            synchronized (p.c.party.aChar) {
                                for (byte i = 0; i < p.c.party.aChar.size(); ++i) {
                                    if (p.c.party.aChar.get(i).level < 50 || p.c.party.aChar.get(i).level > 59) {
                                        p.conn.sendMessageLog("Th??nh vi??n trong nh??m c?? tr??nh ????? kh??ng ph?? h???p");
                                        return;
                                    }
                                }
                            }
                        }
                        if (p.c.party != null) {
                            if (p.c.party.cave == null) {
                                cave = new Cave(5);
                                p.c.party.openCave(cave, p.c.name);
                            } else {
                                cave = p.c.party.cave;
                            }
                        } else {
                            cave = new Cave(5);
                        }
                        p.c.caveID = cave.caveID;
                    }
                    if (b3 == 4) {
                        if (p.c.level < 60 || p.c.level > 69) {
                            p.conn.sendMessageLog("Tr??nh ????? kh??ng ph?? h???p");
                            return;
                        }
                        if (p.c.party != null && p.c.party.aChar.size() > 1) {
                            p.conn.sendMessageLog("Ho???t ?????ng n??y ch??? ???????c ph??p 1 m??nh.");
                            return;
                        }
                        cave = new Cave(6);
                        p.c.caveID = cave.caveID;
                        p.c.isHangDong6x = 1;
                    }
                    if (b3 == 5) {
                        if (p.c.level < 70 || p.c.level > 89) {
                            p.conn.sendMessageLog("Tr??nh ????? kh??ng ph?? h???p");
                            return;
                        }
                        if (p.c.party != null) {
                            synchronized (p.c.party.aChar) {
                                for (byte i = 0; i < p.c.party.aChar.size(); ++i) {
                                    if (p.c.party.aChar.get(i).level < 70 || p.c.party.aChar.get(i).level > 89) {
                                        p.conn.sendMessageLog("Th??nh vi??n trong nh??m c?? tr??nh ????? kh??ng ph?? h???p");
                                        return;
                                    }
                                }
                            }
                        }
                        if (p.c.party != null) {
                            if (p.c.party.cave == null) {
                                cave = new Cave(7);
                                p.c.party.openCave(cave, p.c.name);
                            } else {
                                cave = p.c.party.cave;
                            }
                        } else {
                            cave = new Cave(7);
                        }
                        p.c.caveID = cave.caveID;
                        p.c.isHangDong6x = 0;
                    }
                    if (b3 == 6) {
                        if (p.c.level < 90 || p.c.level > 130) {
                            p.conn.sendMessageLog("Tr??nh ????? kh??ng ph?? h???p");
                            return;
                        }
                        if (p.c.party != null) {
                            synchronized (p.c.party.aChar) {
                                for (byte i = 0; i < p.c.party.aChar.size(); ++i) {
                                    if (p.c.party.aChar.get(i).level < 90 || p.c.party.aChar.get(i).level > 131) {
                                        p.conn.sendMessageLog("Th??nh vi??n trong nh??m c?? tr??nh ????? kh??ng ph?? h???p");
                                        return;
                                    }
                                }
                            }
                        }
                        if (p.c.party != null) {
                            if (p.c.party.cave == null) {
                                cave = new Cave(9);
                                p.c.party.openCave(cave, p.c.name);
                            } else {
                                cave = p.c.party.cave;
                            }
                        } else {
                            cave = new Cave(9);
                        }
                        p.c.caveID = cave.caveID;
                        p.c.isHangDong6x = 0;
                    }
if (b3 == 7) {
                        if (p.c.level < 130 || p.c.level > 150) {
                            p.conn.sendMessageLog("Tr??nh ????? kh??ng ph?? h???p");
                            return;
                        }
                        if (p.c.party != null) {
                            synchronized (p.c.party.aChar) {
                                for (byte i = 0; i < p.c.party.aChar.size(); ++i) {
                                    if (p.c.party.aChar.get(i).level < 130 || p.c.party.aChar.get(i).level > 151) {
                                        p.conn.sendMessageLog("Th??nh vi??n trong nh??m c?? tr??nh ????? kh??ng ph?? h???p");
                                        return;
                                    }
                                }
                            }
                        }
                        if (p.c.party != null) {
                            if (p.c.party.cave == null) {
                                cave = new Cave(8);
                                p.c.party.openCave(cave, p.c.name);
                            } else {
                                cave = p.c.party.cave;
                            }
                        } else {
                            cave = new Cave(8);
                        }
                        p.c.caveID = cave.caveID;
                        p.c.isHangDong6x = 0;
                    }
                    if (cave != null) {
                        p.c.nCave--;
                        p.c.pointCave = 0;

                        if (p.c.party != null && p.c.party.charID == p.c.id) {
                            if(p.c.party.aChar != null && p.c.party.aChar.size() > 0) {
                                synchronized (p.c.party.aChar) {
                                    Char _char;
                                    for (int i = 0; i < p.c.party.aChar.size(); i++) {
                                        if(p.c.party.aChar.get(i) != null) {
                                            _char = p.c.party.aChar.get(i);
                                            if (_char.id != p.c.id && p.c.tileMap.getNinja(_char.id) != null && _char.nCave > 0 && _char.caveID == -1 && _char.level >= 30 && (int) _char.level / 10 == cave.x) {
                                                _char.nCave--;
                                                _char.pointCave = 0;
                                                _char.caveID = p.c.caveID;
                                                _char.isHangDong6x = p.c.isHangDong6x;
                                                _char.mapKanata = _char.mapid;
                                                _char.countHangDong++;
                                                if (_char.pointUydanh < 5000) {
                                                    _char.pointUydanh += 5;
                                                }
                                                _char.tileMap.leave(_char.p);
                                                cave.map[0].area[0].EnterMap0(_char);
                                                _char.p.setPointPB(_char.pointCave);
                                            }
                                        }
                                    }
                                }
                            }

                        }
                        p.c.mapKanata = p.c.mapid;
                        p.c.countHangDong++;
                        if (p.c.pointUydanh < 5000) {
                            p.c.pointUydanh += 5;
                        }
                        p.c.tileMap.leave(p);
                        cave.map[0].area[0].EnterMap0(p.c);
                    }
                }
                p.setPointPB(p.c.pointCave);
                break;
            }
            case 3: {
//                Service.chatNPC(p, (short) npcid, "Ch???c n??ng ??ang b???o tr??, vui l??ng quay l???i sau!");
//                return;
                switch (b3) {
                    case 0: {
                        if (p.c.isNhanban) {
                            p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.party != null && p.c.party.charID != p.c.id) {
                            Service.chatNPC(p, (short) npcid, "Con kh??ng ph???i tr?????ng nh??m, kh??ng th??? th???c hi???n g???i l???i m???i l??i ????i cho ng?????i/nh??m kh??c");
                            return;
                        }

                        Service.sendInputDialog(p, (short) 2, "Nh???p t??n ?????i th??? c???a con");
                        return;
                    }
                    case 1: {
                        Service.sendLoiDaiList(p.c);
                        return;
                    }
                    case 2: {
                        String alert = "";

                        for (int i = 0; i < DunListWin.dunList.size(); ++i) {
                            int temp = i + 1;
                            alert = alert + temp + ". Phe " + ((DunListWin) DunListWin.dunList.get(i)).win + " th???ng Phe " + ((DunListWin) DunListWin.dunList.get(i)).lose + ".\n";
                        }
                        Server.manager.sendTB(p, "K???t qu???", alert);
                        return;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 4: {
                Service.chatNPC(p, (short) npcid, "V?? kh?? c???a ta c???c s???c b??n. N???u mu???n t??? th?? th?? c??? ?????n ch??? ta!");
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t");
                break;
            }
        }
    }

    public static void npcFuroya(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
                switch(b3) {
                    case 0:
                        p.requestItem(21 - p.c.gender);
                        return;
                    case 1:
                        p.requestItem(23 - p.c.gender);
                        return;
                    case 2:
                        p.requestItem(25 - p.c.gender);
                        return;
                    case 3:
                        p.requestItem(27 - p.c.gender);
                        return;
                    case 4:
                        p.requestItem(29 - p.c.gender);
                        return;
                    default:
                        Service.chatNPC(p, (short)npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                        return;
                }
            case 1:
                Service.chatNPC(p, (short)npcid, "Tan b??n qu???n ??o, m?? n??n, g??ng tay v?? gi??y si??u b???n, si??u r???!");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }
static void npccasino(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                switch (b3) {
                    case 0: {
                        if (p.c.xu > 10000000) {
                            p.c.upxuMessage(-10000000);
                            int x = Util.nextInt(2);
                            if (x == 1) {
                                p.c.upxuMessage(19000000);
                                Manager.chatKTG("V??? Ch???n con nghi???n " + p.c.name + " v???a h???t 20.000.000 xu c???a Casino nh??n ph???m t???t");
                                break;
                            } else {
                                Manager.chatKTG("V??? L??? con nghi???n " + p.c.name + " v???a b??? Casino Lu???c 10.000.000 tr xu C??n c??i n???t");
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Kh??ng c?? xu m?? ????i ch??i");
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (p.c.xu > 10000000) {
                            p.c.upxuMessage(-10000000);
                            int x = Util.nextInt(2);
                            if (x == 1) {
                                p.c.upxuMessage(19000000);
                                Manager.chatKTG("V??? L??? con nghi???n " + p.c.name + " v???a h???t 20.000.000 xu c???a Casino nh??n ph???m t???t");
                                break;
                            } else {
                                Manager.chatKTG("V??? Ch???n con nghi???n " + p.c.name + " v???a b??? Casino Lu???c 10.000.000 tr xu C??n c??i n???t");
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Kh??ng c?? xu m?? ????i ch??i");
                            break;
                        }
                        break;
                    }
                }
                break;
            }
            case 1: {
                switch (b3) {
                    case 0: {
                        if (p.c.xu > 50000000) {
                            p.c.upxuMessage(-50000000);
                            int x = Util.nextInt(2);
                            if (x == 1) {
                                p.c.upxuMessage(90000000);
                                Manager.chatKTG("V??? Ch???n con nghi???n " + p.c.name + " v???a h???t 100.000.000 xu c???a Casino nh??n ph???m t???t");
                                break;
                            } else {
                                Manager.chatKTG("V??? L??? con nghi???n " + p.c.name + " v???a b??? Casino Lu???c 50.000.000 tr xu C??n c??i n???t");
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Kh??ng c?? xu m?? ????i ch??i");
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (p.c.xu > 50000000) {
                            p.c.upxuMessage(-50000000);
                            int x = Util.nextInt(2);
                            if (x == 1) {
                                p.c.upxuMessage(90000000);
                                Manager.chatKTG("V??? L??? con nghi???n " + p.c.name + " v???a h???t 100.000.000 xu c???a Casino nh??n ph???m t???t");
                                break;
                            } else {
                                Manager.chatKTG("V??? Ch???n con nghi???n " + p.c.name + " v???a b??? Casino Lu???c 50.000.000 tr xu C??n c??i n???t");
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Kh??ng c?? xu m?? ????i ch??i");
                            break;
                        }
                        break;
                    }
                }
                break;
            }
            case 2: {
                switch (b3) {
                    case 0: {
                        if (p.c.xu > 100000000) {
                            p.c.upxuMessage(-100000000);
                            int x = Util.nextInt(2);
                            if (x == 1) {
                                p.c.upxuMessage(190000000);
                                Manager.chatKTG("V??? Ch???n con nghi???n " + p.c.name + " v???a h???t 200.000.000 xu c???a Casino Lu???c nh??n ph???m t???t");
                                break;
                            } else {
                                Manager.chatKTG("V??? L??? con nghi???n " + p.c.name + " v???a b??? Casino Lu???c 50.000.000 tr xu C??n c??i n???t");
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Kh??ng c?? xu m?? ????i ch??i");
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (p.c.xu > 100000000) {
                            p.c.upxuMessage(-100000000);
                            int x = Util.nextInt(2);
                            if (x == 1) {
                                p.c.upxuMessage(190000000);
                                Manager.chatKTG("V??? L??? con nghi???n " + p.c.name + " v???a h???t 200.000.000 xu c???a Casino Lu???c nh??n ph???m t???t");
                                break;
                            } else {
                                Manager.chatKTG("V??? Ch???n con nghi???n " + p.c.name + " v???a b??? Casino Lu???c 100.000.000 tr xu C??n c??i n???t");
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Kh??ng c?? xu m?? ????i ch??i");
                            break;
                        }
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (b3) {
                    case 0: {
                        if (p.luong > 10000) {
                            p.upluongMessage(-10000);
                            int x = Util.nextInt(2);
                            if (x == 1) {
                                p.upluongMessage(19000);
                                Manager.chatKTG("V??? Ch???n con nghi???n " + p.c.name + " v???a h???t 20.000 l?????ng c???a Casino Lu???c nh??n ph???m t???t");
                                break;
                            } else {
                                Manager.chatKTG("V??? L??? con nghi???n " + p.c.name + " v???a b??? Casino Lu???c 10.000 l?????ng C??n c??i n???t");
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Kh??ng c?? l?????ng m?? ????i ch??i");
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (p.luong > 10000) {
                            p.upluongMessage(-10000);
                            int x = Util.nextInt(2);
                            if (x == 1) {
                                p.upluongMessage(19000);
                                Manager.chatKTG("V??? L??? con nghi???n " + p.c.name + " v???a h???t 19.000 l?????ng c???a Casino Lu???c nh??n ph???m t???t");
                                break;
                            } else {
                                Manager.chatKTG("V??? Ch???n con nghi???n " + p.c.name + " v???a b??? Casino Lu???c 10.000 l?????ng C??n c??i n???t");
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Kh??ng c?? l?????ng m?? ????i ch??i");
                            break;
                        }
                        break;
                    }
                }
                break;
            }
            case 4: {
                switch (b3) {
                    case 0: {
                        if (p.luong > 50000) {
                            p.upluongMessage(-50000);
                            int x = Util.nextInt(2);
                            if (x == 1) {
                                p.upluongMessage(90000);
                                Manager.chatKTG("V??? Ch???n con nghi???n " + p.c.name + " v???a h???t 100.000 l?????ng c???a Casino Lu???c nh??n ph???m t???t");
                                break;
                            } else {
                                Manager.chatKTG("V??? L??? con nghi???n " + p.c.name + " v???a b??? Casino Lu???c 50.000 l?????ng C??n c??i n???t");
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Kh??ng c?? l?????ng m?? ????i ch??i");
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (p.luong > 50000) {
                            p.upluongMessage(-50000);
                            int x = Util.nextInt(2);
                            if (x == 1) {
                                p.upluongMessage(90000);
                                Manager.chatKTG("V??? L??? con nghi???n " + p.c.name + " v???a h???t 100.000 l?????ng c???a Casino Lu???c nh??n ph???m t???t");
                                break;
                            } else {
                                Manager.chatKTG("V??? Ch???n con nghi???n " + p.c.name + " v???a b??? Casino Lu???c 50.000 l?????ng C??n c??i n???t");
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Kh??ng c?? l?????ng m?? ????i ch??i");
                            break;
                        }
                        break;
                    }
                }
                break;
            }
            case 5: {
                switch (b3) {
                    case 0: {
                        if (p.luong > 100000) {
                            p.upluongMessage(-100000);
                            int x = Util.nextInt(2);
                            if (x == 1) {
                                p.upluongMessage(190000);
                                Manager.chatKTG("V??? Ch???n con nghi???n " + p.c.name + " v???a h???t 200.000 l?????ng c???a Casino Lu???c nh??n ph???m t???t");
                                break;
                            } else {
                                Manager.chatKTG("V??? L??? con nghi???n " + p.c.name + " v???a b??? Casino Lu???c 100.000 l?????ng C??n c??i n???t");
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Kh??ng c?? l?????ng m?? ????i ch??i");
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (p.luong > 100000) {
                            p.upluongMessage(-100000);
                            int x = Util.nextInt(2);
                            if (x == 1) {
                                p.upluongMessage(190000);
                                Manager.chatKTG("V??? L??? con nghi???n " + p.c.name + " v???a h???t 200.000 l?????ng c???a Casino Lu???c nh??n ph???m t???t");
                                break;
                            } else {
                                Manager.chatKTG("V??? Ch???n con nghi???n " + p.c.name + " v???a b??? Casino Lu???c 100.000 l?????ng C??n c??i n???t");
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Kh??ng c?? l?????ng m?? ????i ch??i");
                            break;
                        }
                        break;
                    }
                }
                break;
            }
            
            case 6: {
                switch (b3) {
                    case 0: {
                        if (p.c.quantityItemyTotal(632) < 10) {
                            Service.chatNPC(p, (short) npcid, "C???n 10 Th??i D????ng V?? C???c Ki???m");
                            break;
                        } else if (p.luong < 10000) {
                            Service.chatNPC(p, (short) npcid, "C???n 10000 l?????ng");
                            break;
                        } else {
                            int tl = Util.nextInt(3);
                            if (tl != 1) {
                                Service.chatNPC(p, (short) npcid, "S??? con ??en nh?? b???n m???t con v???y");
                                p.upluongMessage(-10000);
                                p.c.removeItemBags(632, 10);
                                break;
                            } else {
                                Service.chatNPC(p, (short) npcid, "Cu???i c??ng con c??ng c?? b?? k??p Ki???m r???i");
                                final Item itemup = ItemData.itemDefault(397);
                                p.upluongMessage(-10000);
                                p.c.removeItemBags(632, 10);
                                p.c.addItemBag(false, itemup);
                                break;
                            }
                        }
                    }
                    case 1: {
                        if (p.c.quantityItemyTotal(633) < 10) {
                            Service.chatNPC(p, (short) npcid, "C???n 10 Th??i D????ng Thi??n H???a Ti??u");
                            break;
                        } else if (p.luong < 10000) {
                            Service.chatNPC(p, (short) npcid, "C???n 10000 l?????ng");
                            break;
                        } else {
                            int tl = Util.nextInt(3);
                            if (tl != 1) {
                                Service.chatNPC(p, (short) npcid, "S??? con ??en nh?? b???n m???t con v???y");
                                p.upluongMessage(-10000);
                                p.c.removeItemBags(633, 10);
                                break;
                            } else {
                                Service.chatNPC(p, (short) npcid, "Cu???i c??ng con c??ng c?? b?? k??p Ti??u r???i");
                                final Item itemup = ItemData.itemDefault(398);
                                p.upluongMessage(-10000);
                                p.c.removeItemBags(633, 10);
                                p.c.addItemBag(false, itemup);
                                break;
                            }
                        }
                    }
                    case 2: {
                        if (p.c.quantityItemyTotal(636) < 10) {
                            Service.chatNPC(p, (short) npcid, "C???n 10 Th??i D????ng Chi???n L???c ??ao");
                            break;
                        } else if (p.luong < 10000) {
                            Service.chatNPC(p, (short) npcid, "C???n 10000 l?????ng");
                            break;
                        } else {
                            int tl = Util.nextInt(3);
                            if (tl != 1) {
                                Service.chatNPC(p, (short) npcid, "S??? con ??en nh?? b???n m???t con v???y");
                                p.upluongMessage(-10000);
                                p.c.removeItemBags(636, 10);
                                break;
                            } else {
                                Service.chatNPC(p, (short) npcid, "Cu???i c??ng con c??ng c?? b?? k??p ??ao r???i");
                                final Item itemup = ItemData.itemDefault(401);
                                p.upluongMessage(-10000);
                                p.c.removeItemBags(636, 10);
                                p.c.addItemBag(false, itemup);
                                break;
                            }
                        }
                    }
                    case 3: {
                        if (p.c.quantityItemyTotal(637) < 10) {
                            Service.chatNPC(p, (short) npcid, "C???n 10 Th??i D????ng Ho??ng Phong Phi???n");
                            break;
                        } else if (p.luong < 10000) {
                            Service.chatNPC(p, (short) npcid, "C???n 10000 l?????ng");
                            break;
                        } else {
                            int tl = Util.nextInt(3);
                            if (tl != 1) {
                                Service.chatNPC(p, (short) npcid, "S??? con ??en nh?? b???n m???t con v???y");
                                p.upluongMessage(-10000);
                                p.c.removeItemBags(637, 10);
                                break;
                            } else {
                                Service.chatNPC(p, (short) npcid, "Cu???i c??ng con c??ng c?? b?? k??p Qu???t r???i");
                                final Item itemup = ItemData.itemDefault(402);
                                p.upluongMessage(-10000);
                                p.c.removeItemBags(637, 10);
                                p.c.addItemBag(false, itemup);
                                break;
                            }
                        }
                    }
                    case 4: {
                        if (p.c.quantityItemyTotal(634) < 10) {
                            Service.chatNPC(p, (short) npcid, "C???n 10 Th??i D????ng T??ng H???n Dao");
                            break;
                        } else if (p.luong < 10000) {
                            Service.chatNPC(p, (short) npcid, "C???n 10000 l?????ng");
                            break;
                        } else {
                            int tl = Util.nextInt(3);
                            if (tl != 1) {
                                Service.chatNPC(p, (short) npcid, "S??? con ??en nh?? b???n m???t con v???y");
                                p.upluongMessage(-10000);
                                p.c.removeItemBags(634, 10);
                                break;
                            } else {
                                Service.chatNPC(p, (short) npcid, "Cu???i c??ng con c??ng c?? b?? k??p Kunai r???i");
                                final Item itemup = ItemData.itemDefault(399);
                                p.upluongMessage(-10000);
                                p.c.removeItemBags(634, 10);
                                p.c.addItemBag(false, itemup);
                                break;
                            }
                        }
                    }
                    case 5: {
                        if (p.c.quantityItemyTotal(635) < 10) {
                            Service.chatNPC(p, (short) npcid, "C???n 10 Th??i D????ng B??ng Th???n Cung");
                            break;
                        } else if (p.luong < 10000) {
                            Service.chatNPC(p, (short) npcid, "C???n 10000 l?????ng");
                            break;
                        } else {
                            int tl = Util.nextInt(3);
                            if (tl != 1) {
                                Service.chatNPC(p, (short) npcid, "S??? con ??en nh?? b???n m???t con v???y");
                                p.upluongMessage(-10000);
                                p.c.removeItemBags(635, 10);
                                break;
                            } else {
                                Service.chatNPC(p, (short) npcid, "Cu???i c??ng con c??ng c?? b?? k??p Cung r???i");
                                final Item itemup = ItemData.itemDefault(400);
                                p.upluongMessage(-10000);
                                p.c.removeItemBags(635, 10);
                                p.c.addItemBag(false, itemup);
                                break;
                        }
                    }
                }
            }
        }
          default: {
                Server.manager.sendTB(p, "H?????ng d???n", "Kho???ng c??y b??t vi???t th?? t???ng b???n Ch??c T??n Ni??n c?? v???n ni???m vui Bao nhi??u v???t v??? ?????y l??i Thay v??o l?? nh???ng ng???t b??i y??u th????ng H??m nay l?? T???t D????ng l???ch ???? G???i l???i ch??c nh??? gi?? chuy???n cho Mong m???i ng?????i h???t s???u lo B??nh an h???nh ph??c chuy???n ???? nh??n gian M???t... hai... ba, c??ng san s??? T???t Ta n??ng ly qu??n h???t bu???n ?????i Ch??c cho cu???c s???ng tuy???t v???i T??nh b???n tri k??? ng?????i ??i gi??? g??n H??y ?????t nh???ng ni???m tin y??u qu?? S???ng ch??n th??nh, hoan h??? m???i ng??y Th??? s??? c?? l???m ?????i thay T??m ta b???t bi???n, th???ng ngay m?? l??m G???i ch??c ng?????i Vi???t Nam y??u d???u N??m T??n s???u ph???n ?????u m???i ??i???u L??m nh???ng c??ng vi???c m??nh y??u ????? cho cu???c s???ng th??m nhi???u b??nh y??n -T???t 2022 Ch??c M???i Ng?????i May M???n !!");
                break;
            }
        }
    }

    public static void npcCLXTCoin(Player p, byte npcid, byte menuId, byte b3) throws IOException, SQLException {
        switch(menuId) {
            case 0: {
                switch (b3) {
                    case 0: {
                        Service.sendInputDialog(p, (short) 44_0_0, "Nh???p s??? coin ?????t (ph???i l?? b???i s??? c???a 10) :");
                        break;
                    }
                    case 1: {
                        Service.sendInputDialog(p, (short) 44_0_1, "Nh???p s??? coin ?????t (ph???i l?? b???i s??? c???a 10) :");
                        break;
                    }
                    case 2: {
                        try {
                            String a = "";
                            int i2 = 1;
                            for (CheckTXCoin check: CheckTXCoin.checkTXCoinArrayList) {
                                a += i2 + ". " + check.name + " - " + check.item + " - " + check.time +".\n";
                                i2++;
                            }
                            Server.manager.sendTB(p, "Soi C???u", a);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            }
            case 1: {
                switch (b3) {
                    case 0: {
                        Service.sendInputDialog(p, (short) 44_1_0, "Nh???p s??? coin ?????t (ph???i l?? b???i s??? c???a 10) :");
                        break;
                    }
                    case 1: {
                        Service.sendInputDialog(p, (short) 44_1_1, "Nh???p s??? coin ?????t (ph???i l?? b???i s??? c???a 10) :");
                        break;
                    }
                    case 2: {
                        try {
                            String a = "";
                            int i2 = 1;
                            for (CheckCLCoin check: CheckCLCoin.checkCLCoinArrayList) {
                                a += i2 + ". " + check.name + " - " + check.item + " - " + check.time +".\n";
                                i2++;
                            }
                            Server.manager.sendTB(p, "Soi C???u", a);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            }
            case 2: {
                try {
                    synchronized(Server.LOCK_MYSQL) {
                        ResultSet red = SQLManager.stat.executeQuery("SELECT `coin` FROM `player` WHERE `id` = "+p.id+";");
                        if (red != null && red.first()) {
                            p.coin = red.getInt("coin");
                            p.conn.sendMessageLog("B???n ??ang c?? : " + p.coin +". H??y tho??t ra v??o l???i ????? c???p nh???t coin m???i nh???t n???u n???p.");
                            break;
                        }
                    }
                } catch (SQLException var17) {
                    var17.printStackTrace();
                    p.conn.sendMessageLog("L???i.");
                }
                break;
            }
            case 3: {
                Server.manager.sendTB(p, "H?????ng d???n", "????y l?? NPC ch??i CLTX b???ng coin."
                        + "\nM???i l???n ?????t c?????c gi?? tr??? ph???i l?? b???i s??? c???a 10 (20,30,40,...)\n"
                        + "N???u may m???n chi???n th???ng b???n s??? nh???n ???????c 1,9 s??? coin c?????c.\n"
                        + "N???u thua b???n m??o ???????c g??.\n"
                        + "????? c?? coin ch??i b???n c???n l??n web teamobi.cf n???p coin ho???c ib admin.\n"
                        + "Ch??c B???n S???m Tr??? Th??nh Tr??m VIP!");
                break;
            }
        }
    }
    
    public static void npcCLXTLuong(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                switch (b3) {
                    case 0: {
                        Service.sendInputDialog(p, (short) 45_0_0, "Nh???p s??? l?????ng ?????t (ph???i l?? b???i s??? c???a 10) :");
                        break;
                    }
                    case 1: {
                        Service.sendInputDialog(p, (short) 45_0_1, "Nh???p s??? l?????ng ?????t (ph???i l?? b???i s??? c???a 10) :");
                        break;
                    }
                    case 2: {
                        try {
                            String a = "";
                            int i2 = 1;
                            for (CheckTXLuong check: CheckTXLuong.checkTXLuongArrayList) {
                                a += i2 + ". " + check.name + " - " + check.item + " - " + check.time +".\n";
                                i2++;
                            }
                            Server.manager.sendTB(p, "Soi C???u", a);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            }
            case 1: {
                switch (b3) {
                    case 0: {
                        Service.sendInputDialog(p, (short) 45_1_0, "Nh???p s??? l?????ng ?????t (ph???i l?? b???i s??? c???a 10) :");
                        break;
                    }
                    case 1: {
                        Service.sendInputDialog(p, (short) 45_1_1, "Nh???p s??? l?????ng ?????t (ph???i l?? b???i s??? c???a 10) :");
                        break;
                    }
                    case 2: {
                        try {
                            String a = "";
                            int i2 = 1;
                            for (CheckCLLuong check: CheckCLLuong.checkCLLuongArrayList) {
                                a += i2 + ". " + check.name + " - " + check.item + " - " + check.time +".\n";
                                i2++;
                            }
                            Server.manager.sendTB(p, "Soi C???u", a);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
    public static void npcAmeji(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                switch (b3) {
                    case 0: {
                        p.requestItem(16);
                        break;
                    }
                    case 1: {
                        p.requestItem(17);
                        break;
                    }
                    case 2: {
                        p.requestItem(18);
                        break;
                    }
                    case 3: {
                        p.requestItem(19);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 1: {
                ItemTemplate data;
                switch (b3) {
                    case 0: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.level < 50) {
                            Service.chatNPC(p, (short) npcid, "C???p ????? c???a con kh??ng ????? ????? nh???n nhi???m v??? n??y");
                            return;
                        }

                        if (p.c.countTaskDanhVong < 1) {
                            Service.chatNPC(p, (short) npcid, "S??? l???n nh???n nhi???m v??? c???a con h??m nay ???? h???t");
                            return;
                        }

                        if (p.c.isTaskDanhVong == 1) {
                            Service.chatNPC(p, (short) npcid, "Tr?????c ???? con ???? nh???n nhi???m v??? r???i, h??y ho??n th??nh ???? nha");
                            return;
                        }

                        int type = DanhVongTemplate.randomNVDV();
                        p.c.taskDanhVong[0] = type;
                        p.c.taskDanhVong[1] = 0;
                        p.c.taskDanhVong[2] = DanhVongTemplate.targetTask(type);
                        p.c.isTaskDanhVong = 1;
                        p.c.countTaskDanhVong--;
                        if (p.c.isTaskDanhVong == 1) {
                            String nv = "NHI???M V??? L???N N??Y: \n" +
                                    String.format(DanhVongTemplate.nameNV[p.c.taskDanhVong[0]],
                                            p.c.taskDanhVong[1],
                                            p.c.taskDanhVong[2])
                                    + "\n\n- S??? l???n nh???n nhi???m v??? c??n l???i l??: " + p.c.countTaskDanhVong;
                            Server.manager.sendTB(p, "Nhi???m v???", nv);
                        }
                        break;
                    }
                    case 1: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isTaskDanhVong == 0) {
                            Service.chatNPC(p, (short) npcid, "Con ch??a nh???n nhi???m v??? n??o c???!");
                            return;
                        }

                        if (p.c.taskDanhVong[1] < p.c.taskDanhVong[2]) {
                            Service.chatNPC(p, (short) npcid, "Con ch??a ho??n th??nh nhi???m v??? ta giao!");
                            return;
                        }

                        if (p.c.getBagNull() < 2) {
                            Service.chatNPC(p, (short) npcid, "H??nh trang c???a con kh??ng ????? ch??? tr???ng ????? nh???n th?????ng");
                            return;
                        }
                        int point = 0;
                        switch(p.vip){
                            case 0:{
                                point = 3;
                                break;
                            }
                            case 1:{
                                point = 50;
                                break;
                            }
                            case 2:{
                                point = 60;
                                break;
                            }
                            case 3:{
                                point = 70;
                                break;
                            }
                            case 4:{
                                point = 80;
                                break;
                            }
                            case 5:{
                                point = 90;
                                break;
                            }
                            case 6:{
                                point = 100;
                                break;
                            }
                        }
                        if (p.c.taskDanhVong[0] == 9) {
                            if(p.vip < 1){
                                point = 5;
                            }
                            else{
                                point = 150;
                            }
                        }

                        p.c.isTaskDanhVong = 0;
                        p.c.taskDanhVong = new int[]{-1, -1, -1, 0, p.c.countTaskDanhVong};
                        Item item = ItemTemplate.itemDefault(DanhVongTemplate.randomDaDanhVong(), false);
                        item.quantity = 1;
                        item.isLock = false;
                        if (p.c.pointUydanh < 5000) {
                            ++p.c.pointUydanh;
                        }

                        p.c.addItemBag(true, item);
                        int type = Util.nextInt(10);

                        if (p.c.avgPointDanhVong(p.c.getPointDanhVong(type))) {
                            for (int i = 0; i < 10; i++) {
                                type = i;
                                if (!p.c.avgPointDanhVong(p.c.getPointDanhVong(type))) {
                                    break;
                                }
                            }
                        }
                        p.c.plusPointDanhVong(type, point);

                        if(p.c.countTaskDanhVong % 2 == 0) {
                            Item itemUp = ItemTemplate.itemDefault(p.c.gender == 1 ? 739 : 766, true);
                            itemUp.isLock = true;
                            itemUp.isExpires = false;
                            itemUp.expires = -1L;
                            itemUp.quantity = Util.nextInt(1,2);
                            p.c.addItemBag(true, itemUp);
                        } else {
                            Item itemUp = ItemTemplate.itemDefault(p.c.gender == 1 ? 740 : 767, true);
                            itemUp.isLock = true;
                            itemUp.isExpires = false;
                            itemUp.expires = -1L;
                            itemUp.quantity = Util.nextInt(1,2);
                            p.c.addItemBag(true, itemUp);
                        }

                        Service.chatNPC(p, (short) npcid, "Con h??y nh???n l???y ph???n th?????ng c???a m??nh.");
                        break;
                    }
                    case 2: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isTaskDanhVong == 0) {
                            Service.chatNPC(p, (short) npcid, "Con ch??a nh???n nhi???m v??? n??o c???!");
                            return;
                        }

                        Service.startYesNoDlg(p, (byte) 2, "Con c?? ch???c ch???n mu???n hu??? nhi???m v??? l???n n??y kh??ng?");
                        break;
                    }
                    case 3: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.checkPointDanhVong(1)) {
                            if (p.c.getBagNull() < 1) {
                                Service.chatNPC(p, (short) npcid, "H??nh trang c???a con kh??ng ????? ch??? tr???ng ????? nh???n th?????ng");
                                return;
                            }

                            Item item = ItemTemplate.itemDefault(685, true);
                            item.quantity = 1;
                            item.upgrade = 1;
                            item.isLock = true;
                            Option op = new Option(6, 1000);
                            item.options.add(op);
                            op = new Option(87, 500);
                            item.options.add(op);
                            p.c.addItemBag(false, item);
                        } else {
                            Service.chatNPC(p, (short) npcid, "Con ch??a ????? ??i???m ????? nh???n m???t");
                        }

                        break;
                    }
                    case 4: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.ItemBody[14] == null) {
                            Service.chatNPC(p, (short) npcid, "H??y ??eo m???t v??o ng?????i tr?????c r???i n??ng c???p nh??.");
                            return;
                        }

                        if (p.c.ItemBody[14] == null) {
                            return;
                        }

                        if (p.c.ItemBody[14].upgrade >= 10) {
                            Service.chatNPC(p, (short) npcid, "M???t c???a con ???? ?????t c???p t???i ??a");
                            return;
                        }

                        if (!p.c.checkPointDanhVong(p.c.ItemBody[14].upgrade)) {
                            Service.chatNPC(p, (short) npcid, "Con ch??a ????? ??i???m danh v???ng ????? th???c hi???n n??ng c???p");
                            return;
                        }

                        data = ItemTemplate.ItemTemplateId(p.c.ItemBody[14].id);
                        Service.startYesNoDlg(p, (byte) 0, "B???n c?? mu???n n??ng c???p " + data.name + " v???i " + GameSrc.coinUpMat[p.c.ItemBody[14].upgrade] + " y??n ho???c xu v???i t??? l??? th??nh c??ng l?? " + GameSrc.percentUpMat[p.c.ItemBody[14].upgrade] + "% kh??ng?");
                        break;
                    }
                    case 5: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.ItemBody[14] == null) {
                            Service.chatNPC(p, (short) npcid, "H??y ??eo m???t v??o ng?????i tr?????c r???i n??ng c???p nh??.");
                            return;
                        }

                        if (p.c.ItemBody[14].upgrade >= 10) {
                            Service.chatNPC(p, (short) npcid, "M???t c???a con ???? ?????t c???p t???i ??a");
                            return;
                        }

                        if (!p.c.checkPointDanhVong(p.c.ItemBody[14].upgrade)) {
                            Service.chatNPC(p, (short) npcid, "Con ch??a ????? ??i???m danh v???ng ????? th???c hi???n n??ng c???p");
                            return;
                        }

                        data = ItemTemplate.ItemTemplateId(p.c.ItemBody[14].id);
                        Service.startYesNoDlg(p, (byte) 1, "B???n c?? mu???n n??ng c???p " + data.name + " v???i " + GameSrc.coinUpMat[p.c.ItemBody[14].upgrade] + " y??n ho???c xu v?? " + GameSrc.goldUpMat[p.c.ItemBody[14].upgrade] + " l?????ng v???i t??? l??? th??nh c??ng l?? " + GameSrc.percentUpMat[p.c.ItemBody[14].upgrade] * 2 + "% kh??ng?");
                        break;
                    }
                    case 6: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        String nv = "- Ho??n th??nh nhi???m v???. H??y g???p Ameji ????? tr??? nhi???m v???.\n- H??m nay c?? th??? nh???n th??m " + p.c.countTaskDanhVong + " nhi???m v??? trong ng??y.\n- H??m nay c?? th??? s??? d???ng th??m " + p.c.useDanhVongPhu + " Danh V???ng Ph?? ????? nh???n th??m 5 l???n l??m nhi???m v???.\n- Ho??n th??nh nhi???m v??? s??? nh???n ng???u nhi??n 1 vi??n ???? danh v???ng c???p 1-10.\n- Khi ????? m???c 100 ??i???m m???i lo???i c?? th??? nh???n m???t v?? n??ng c???p m???t.";
                        if (p.c.isTaskDanhVong == 1) {
                            nv = "NHI???M V??? L???N N??Y: \n" + String.format(DanhVongTemplate.nameNV[p.c.taskDanhVong[0]], p.c.taskDanhVong[1], p.c.taskDanhVong[2]) + "\n\n" + nv;
                        }

                        Server.manager.sendTB(p, "Nhi???m v???", nv);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 2: {
                Service.chatNPC(p, (short) npcid, "Tan b??n c??c lo???i trang s???c l???p l??nh!");
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcKiriko(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                p.requestItem(7);
                break;
            }
            case 1: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                p.requestItem(6);
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcTabemono(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                p.requestItem(9);
                break;
            case 1:
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                p.requestItem(8);
                break;
            case 2: {
                Service.chatNPC(p, (short) npcid, "3 ?????i nh?? ta b??n th???c ??n ch??a ai b??? ??au b???ng c???!");
                break;
            }
            case 3: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                switch (b3) {
                    case 0: {
                        if (!ThienDiaBangManager.register) {
                            Service.chatNPC(p, (short) npcid, "??ang trong th???i gian t???ng k???t. Hi???n t???i kh??ng th??? ????ng k??.");
                            return;
                        }
                        if (ThienDiaBangManager.diaBangList.containsKey(p.c.name) || ThienDiaBangManager.thienBangList.containsKey(p.c.name)) {
                            Service.chatNPC(p, (short) npcid, "Con ???? ????ng k?? tr?????c ???? r???i");
                            return;
                        }
                        if (p.c.get().level >= 50 && p.c.get().level < 70) {
                            ThienDiaBangManager.diaBangList.put(p.c.name, new ThienDiaData(p.c.name, ThienDiaBangManager.rankDiaBang++, 1));
                            Service.chatNPC(p, (short) npcid, "Con ???? ????ng k?? tham gia trang t??i ?????a b???ng th??nh c??ng.");
                        } else if (p.c.get().level >= 70) {
                            ThienDiaBangManager.thienBangList.put(p.c.name, new ThienDiaData(p.c.name, ThienDiaBangManager.rankThienBang++, 1));
                            Service.chatNPC(p, (short) npcid, "Con ???? ????ng k?? tham gia tranh t??i Thi??n b???ng th??nh c??ng.");
                        } else {
                            Service.chatNPC(p, (short) npcid, "Tr??nh ????? c???a con kh??ng ph?? h???p ????? ????ng k?? tham gia tranh t??i.");
                        }
                        break;
                    }
                    case 1: {
                        if (!ThienDiaBangManager.register) {
                            Service.chatNPC(p, (short) npcid, "??ang trong th???i gian t???ng k???t. Hi???n t???i kh??ng th??? thi ?????u.");
                            return;
                        }
                        ArrayList<ThienDiaData> list = new ArrayList<>();
                        if (ThienDiaBangManager.diaBangList.containsKey(p.c.name)) {
                            ThienDiaData rank = ThienDiaBangManager.diaBangList.get(p.c.name);
                            for (ThienDiaData data : ThienDiaBangManager.getListDiaBang()) {
                                if (data != null) {
                                    if (rank.getRank() < 10 && (data.getRank() - rank.getRank()) < 20) {
                                        list.add(data);
                                    } else if (data.getRank() < rank.getRank() & (rank.getRank() - data.getRank()) < 10) {
                                        list.add(data);
                                    }
                                }
                            }
                        } else if (ThienDiaBangManager.thienBangList.containsKey(p.c.name)) {
                            ThienDiaData rank = ThienDiaBangManager.thienBangList.get(p.c.name);
                            for (ThienDiaData data : ThienDiaBangManager.getListThienBang()) {
                                if (data != null) {
                                    if (rank.getRank() < 10 && (data.getRank() - rank.getRank()) < 20) {
                                        list.add(data);
                                    } else if (data.getRank() <= rank.getRank() & (rank.getRank() - data.getRank()) < 10) {
                                        list.add(data);
                                    }
                                }
                            }
                        } else {
                            Service.chatNPC(p, (short) npcid, "Con ch??a ????ng k?? tham gia thi ?????u.");
                            return;
                        }
                        Service.SendChinhPhuc(p, list);
                        return;
                    }
                    case 2: {
                        String res = "";
                        int count = 1;
                        for (ThienDiaData data : ThienDiaBangManager.getListSortAsc(new ArrayList<ThienDiaData>(ThienDiaBangManager.thienBangList.values()))) {
                            if (count < 11) {
                                res += "H???ng " + count + ": " + data.getName() + ".\n";
                                count++;
                            }
                        }
                        Server.manager.sendTB(p, "Thi??n b???ng", res);
                        return;
                    }
                    case 3: {
                        String res = "";
                        int count = 1;
                        for (ThienDiaData data : ThienDiaBangManager.getListSortAsc(new ArrayList<ThienDiaData>(ThienDiaBangManager.diaBangList.values()))) {
                            if (count < 11) {
                                res += "H???ng " + count + ": " + data.getName() + ".\n";
                                count++;
                            }
                        }
                        Server.manager.sendTB(p, "?????a b???ng", res);
                        return;
                    }
                    case 4: {
                        ResultSet res = null;
                        try {
                        if (true){//if(Manager.nhanquatdb == 0){
                           Service.chatNPC(p, (short) npcid, "Ch??? nh???n qu?? ???????c v??o th??? 2.");
                                return;
                            }
                            if(p.c.rankTDB > 0) {
                                if(p.c.isGiftTDB == 1) {
                                    if(p.c.rankTDB > 20) {
                                        p.upluongMessage(500);
                                        p.c.upxuMessage(500000);
                                    } else {
                                        switch (p.c.rankTDB) {
                                            case 1: {
                                                if(p.c.getBagNull() < 10) {
                                                    Service.chatNPC(p, (short) npcid, "Con c???n ??t nh???t 10 ch??? tr???ng trong h??nh trang ????? nh???n th?????ng.");
                                                    return;
                                                }
                                                Item pl = ItemTemplate.itemDefault(308,false);
                                                pl.quantity = 2;
                                                p.c.addItemBag(true,pl);

                                                pl = ItemTemplate.itemDefault(309,false);
                                                pl.quantity = 2;
                                                p.c.addItemBag(true,pl);

                                                p.c.addItemBag(false,ItemTemplate.itemDefault(540,false));
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(540,false));
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(539,false));
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(539,false));
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(539,false));

                                                p.c.addItemBag(false,ItemTemplate.itemDefault(384,false));
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(383,false));
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(383,false));

                                                p.upluongMessage(20000);
                                                p.c.upxuMessage(20000000);
                                                break;
                                            }
                                            case 2: {
                                                if(p.c.getBagNull() < 7) {
                                                    Service.chatNPC(p, (short) npcid, "Con c???n ??t nh???t 7 ch??? tr???ng trong h??nh trang ????? nh???n th?????ng.");
                                                    return;
                                                }
                                                Item pl = ItemTemplate.itemDefault(308,false);
                                                pl.quantity = 1;
                                                p.c.addItemBag(true,pl);

                                                pl = ItemTemplate.itemDefault(309,false);
                                                pl.quantity = 1;
                                                p.c.addItemBag(true,pl);

                                                p.c.addItemBag(false,ItemTemplate.itemDefault(540,false));
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(539,false));
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(539,false));

                                                p.c.addItemBag(false,ItemTemplate.itemDefault(384,false));
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(383,false));

                                                p.upluongMessage(10000);
                                                p.c.upxuMessage(10000000);
                                                break;
                                            }
                                            case 3: {
                                                if(p.c.getBagNull() < 4) {
                                                    Service.chatNPC(p, (short) npcid, "Con c???n ??t nh???t 4 ch??? tr???ng trong h??nh trang ????? nh???n th?????ng.");
                                                    return;
                                                }
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(540,false));
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(539,false));
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(383,false));
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(383,false));
                                                p.upluongMessage(5000);
                                                p.c.upxuMessage(5000000);
                                                break;
                                            }
                                            case 4:
                                            case 5:
                                            case 6:
                                            case 7:
                                            case 8:
                                            case 9:
                                            case 10: {
                                                if(p.c.getBagNull() < 4) {
                                                    Service.chatNPC(p, (short) npcid, "Con c???n ??t nh???t 2 ch??? tr???ng trong h??nh trang ????? nh???n th?????ng.");
                                                    return;
                                                }
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(539,false));
                                                p.c.addItemBag(false,ItemTemplate.itemDefault(383,false));
                                                p.upluongMessage(3000);
                                                p.c.upxuMessage(3000000);
                                                break;
                                            }
                                            case 11:
                                            case 12:
                                            case 13:
                                            case 14:
                                            case 15:
                                            case 16:
                                            case 17:
                                            case 18:
                                            case 19:
                                            case 20: {
                                                p.upluongMessage(1000);
                                                p.c.upxuMessage(1000000);
                                                break;
                                            }

                                        }
                                    }
                                    p.c.isGiftTDB = 0;
                                } else {
                                    Service.chatNPC(p, (short) npcid, "Con ???? nh???n th?????ng tu???n r???i.");
                                    return;
                                }
                            } else {
                                Service.chatNPC(p, (short) npcid, "Tu???n tr?????c con ch??a tham gia Thi??n ?????a b???ng v?? ch??a c?? rank, con ch??a th??? nh???n th?????ng.");
                                return;
                            }
                        } catch (Exception e) {
                            p.conn.sendMessageLog("L???i nh???n th?????ng, vui l??ng th??? l???i sau!");
                            return;
                        } finally {
                            if(res != null) {
                                try {
                                    res.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        break;
                    }
                    case 5: {
                        Server.manager.sendTB(p, "H?????ng d???n", "- Thi??n ?????a B???ng s??? ???????c m??? h??ng tu???n. B???t ?????u t??? th??? 2 v?? t???ng k???t v??o ch??? nh???t.\n" +
                                "- Thi??n ?????a B???ng s??? ???????c m??? ????ng k?? v?? ch??nh ph???c t??? 00h05' ?????n 23h45' h??ng ng??y. M???i ng??y s??? c?? 20p ????? t???ng k???t ng??y, trong th???i gian n??y s??? kh??ng th??? ????ng k?? v?? chinh ph???c\n" +
                                "- Trong th???i gian t???ng k???t n???u chi???n th???ng trong Chinh ph???c s??? kh??ng ???????c t??nh rank." +
                                "- V??o ng??y th?????ng s??? kh??ng gi???i h???n l?????t th??ch ?????u.\n" +
                                "- V??o Th??? 7 v?? Ch??? Nh???t m???i Ninja s??? c?? 5 l?????t th??ch ?????u, Th???ng s??? kh??ng b??? m???t l?????t, thua s??? b??? tr??? 1 l???n th??ch ?????u." +
                                "- ?????a B???ng d??nh cho ninja t??? c???p ????? 50-69.\n" +
                                "- Thi??n B???ng d??nh cho ninja t??? c???p ????? tr??n 70\n" +
                                "- Sau khi ????ng k?? th??nh c??ng, h??y Chinh Ph???c ngay ????? gi??nh l???y v??? tr?? top ?????u.\n" +
                                "- M???i l???n chi???n th???ng, n???u v??? tr?? c???a ?????i th??? tr?????c b???n, b???n s??? ?????i v??? tr?? c???a m??nh cho ?????i th???, c??n kh??ng v??? tr?? c???a b???n s??? ???????c gi??? nguy??n.\n" +
                                "- Ph???n th?????ng s??? ???????c tr??? th?????ng v??o m???i tu???n m???i (L??u ??: H??y nh???n th?????ng ngay trong tu???n m???i ????, n???u sang tu???n sau ph???n th?????ng s??? b??? reset).\n\n" +
                                "- PH???N TH?????NG: \n" +
                                "Top 1: H??o quang Rank 1 + 2 B??nh Phong L??i, 2 B??nh B??ng Ho???, 2 N???m x4, 3 N???m x3, 1 R????ng b???ch ng??n, 2 B??t b???o, 20,000 L?????ng, 20,000,000 xu.\n\n" +
                                "Top 2: H??o quang Rank 2 + 1 B??nh Phong L??i, 1 B??nh B??ng Ho???, 1 N???m x4, 2 N???m x3, 1 R????ng b???ch ng??n, 1 B??t b???o, 10,000 L?????ng, 10,000,000 xu.\n\n" +
                                "Top 3: H??o quang Rank 3 + 1 N???m x4, 1 N???m x3, 2 B??t b???o, 5,000 L?????ng, 5,000,000 xu.\n\n" +
                                "Top 4-10: 1 N???m x3, 1 B??t b???o, 3,000 L?????ng, 3,000,000 xu.\n\n" +
                                "Top 11-20: 1,000 L?????ng, 1,000,000 xu.\n\n" +
                                "C??n l???i: 500 L?????ng, 500,000 xu.");
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcKamakura(Player p, byte npcid, byte menuId, byte b3) {
        try {
            if (p.c.isNhanban) {
                p.conn.sendMessageLog("Ch???c n??ng n??y kh??ng d??nh cho ph??n th??n.");
                return;
            }
            switch(menuId) {
                case 0:
                    //p.requestItem(4);
                    switch (b3) {
                        case 0: {
                            Service.openMenuBox(p);
                            break;
                        }
                        case 1: {
                            Service.openMenuBST(p);
                            break;
                        }
                        case 2: {
                            Service.openMenuCaiTrang(p);
                            break;
                        }
                        case 3: {
                            //Th??o c???i trang
                            p.c.caiTrang = -1;
                            Message m = new Message(11);
                            m.writer().writeByte(-1);
                            m.writer().writeByte(p.c.get().speed());
                            m.writer().writeInt(p.c.get().getMaxHP());
                            m.writer().writeInt(p.c.get().getMaxMP());
                            m.writer().writeShort(p.c.get().eff5buffHP());
                            m.writer().writeShort(p.c.get().eff5buffMP());
                            m.writer().flush();
                            p.conn.sendMessage(m);
                            m.cleanup();
                            Service.CharViewInfo(p, false);
                            p.endLoad(true);
                            break;
                        }
                    }
                    break;
                case 1:
                    if(p.c.tileMap.map.getXHD() != -1 || p.c.tileMap.map.LangCo() || p.c.tileMap.map.mapBossTuanLoc() || p.c.tileMap.map.mapLDGT() || p.c.tileMap.map.mapGTC() || p.c.tileMap.map.id == 111 || p.c.tileMap.map.id == 113) {
                        p.c.mapLTD = 22;
                    } else {
                        p.c.mapLTD = p.c.tileMap.map.id;
                    }
                    Service.chatNPC(p, (short)npcid, "L??u to??? ????? th??nh c??ng! Khi ch???t con s??? ???????c v??c x??c v??? ????y.");
                    break;
                case 2:
                    switch(b3) {
                        case 0:
                            if (p.c.level < 60) {
                                p.conn.sendMessageLog("Ch???c n??ng n??y y??n c???u tr??nh ????? 60");
                                return;
                            }

                            Map ma = Manager.getMapid(139);
                            TileMap area;
                            int var8;
                            for(var8 = 0; var8 < ma.area.length; ++var8) {
                                area = ma.area[var8];
                                if (area.numplayers < ma.template.maxplayers) {
                                    p.c.tileMap.leave(p);
                                    area.EnterMap0(p.c);
                                    return;
                                }
                            }
                            return;
                        case 1:
                            Server.manager.sendTB(p, "H?????ng d???n", "- H?????ng d???n v??ng ?????t ma qu???");
                            return;
                        default:
                            return;
                    }
                case 3:
                    Service.chatNPC(p, (short)npcid, "Ta gi??? ????? ch??a bao gi??? b??? m???t th??? g?? c???!");
                    break;
                default: {
                    Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void npcKenshinto(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        if(p.c.isNhanban) {
            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
            return;
        }
        switch(menuId) {
            case 0: {
                if (b3 == 0) {
                    p.requestItem(10);
                } else if (b3 == 1) {
                    p.requestItem(31);
                } else if (b3 == 2) {
                    Server.manager.sendTB(p, "H?????ng d???n", "- H?????ng d???n n??ng c???p trang b???");
                }
                break;
            }
            case 1: {
                if (b3 == 0) {
                    p.requestItem(12);
                } else if (b3 == 1) {
                    p.requestItem(11);
                }
                break;
            }
            case 2: {
                p.requestItem(13);
                break;
            }
            case 3: {
                p.requestItem(33);
                break;
            }
            case 4: {
                p.requestItem(46);
                break;
            }
            case 5: {
                p.requestItem(47);
                break;
            }
            case 6: {
                p.requestItem(49);
                break;
            }
            case 7: {
                p.requestItem(50);
                break;
            }
            case 8: {
                Service.chatNPC(p, (short) npcid, "C???n n??ng c???p trang b???, h??y ?????n qu??n c???a ta!");
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcUmayaki_Lang(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
                Service.chatNPC(p, (short)npcid, "Ta k??o xe qua c??c l??ng v???i t???c ????? ??nh s??ng!");
                return;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                TileMap[] var5 = Manager.getMapid(Map.arrLang[menuId - 1]).area;
                int var6 = var5.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    TileMap area = var5[var7];
                    if (area.numplayers < Manager.getMapid(Map.arrLang[menuId - 1]).template.maxplayers) {
                        p.c.tileMap.leave(p);
                        area.EnterMap0(p.c);
                        return;
                    }
                }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
    }

    public static void npcUmayaki_Truong(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
            case 1:
            case 2:
                TileMap[] var5 = Manager.getMapid(Map.arrTruong[menuId]).area;
                int var6 = var5.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    TileMap area = var5[var7];
                    if (area.numplayers < Manager.getMapid(Map.arrTruong[menuId]).template.maxplayers) {
                        p.c.tileMap.leave(p);
                        area.EnterMap0(p.c);
                        return;
                    }
                }

                return;
            case 3:
                Service.chatNPC(p, (short)npcid, "Ta k??o xe qua c??c tr?????ng, kh??ng qua qu??n net ????u!");
                return;
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
    }

    public static void npcToyotomi(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
                switch(b3) {
                    case 0:
                        Server.manager.sendTB(p, "Top ?????i gia y??n", Rank.getStringBXH(0));
                        return;
                    case 1:
                        Server.manager.sendTB(p, "Top cao th???", Rank.getStringBXH(1));
                        return;
                    case 2:
                        Server.manager.sendTB(p, "Top gia t???c", Rank.getStringBXH(2));
                        return;
                    case 3:
                        Server.manager.sendTB(p, "Top hang ?????ng", Rank.getStringBXH(3));
                        return;
                    default:
                        return;
                }
            case 1:
                if (p.c.get().nclass > 0) {
                    Service.chatNPC(p, (short)npcid, "Con ???? v??o l???p t??? tr?????c r???i m??.");
                } else if (p.c.get().ItemBody[1] != null) {
                    Service.chatNPC(p, (short)npcid, "Con c???n c?? 1 t??m h???n trong tr???ng m???i c?? th??? nh???p h???c, h??y th??o v?? kh?? tr??n ng?????i ra!");
                } else if (p.c.getBagNull() < 2) {
                    Service.chatNPC(p, (short)npcid, "H??nh trang c???n ph???i c?? ??t nh???t 2 ?? tr???ng m???i c?? th??? nh???p h???c!");
                } else {
                    if (b3 == 0) {
                        Admission.Admission(p,(byte)1);
                    } else {
                        if (b3 != 1) {
                            Service.chatNPC(p, (short)npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                            break;
                        }
                        Admission.Admission(p,(byte)2);
                    }

                    Service.chatNPC(p, (short)npcid, "H??y ch??m ch??? luy???n t???p, c?? l??m th?? m???i c?? ??n con nh??.");
                }
                break;
            case 2:
                if (p.c.get().nclass != 1 && p.c.get().nclass != 2) {
                    Service.chatNPC(p, (short)npcid, "Con kh??ng ph???i h???c sinh c???a tr?????ng n??y, ta kh??ng th??? gi??p con t???y ??i???m d?????c r???i.");
                } else if (b3 == 0) {
                    if (p.c.get().countTayTiemNang < 1) {
                        Service.chatNPC(p, (short)npcid, "S??? l???n t???y ??i???m k??? n??ng c???a con ???? h???t.");
                        return;
                    }
                    p.restPpoint();
                    --p.c.get().countTayTiemNang;
                    Service.chatNPC(p, (short)npcid, "Ta ???? gi??p con t???y ??i???m ti???m n??ng, h??y n??ng ??i???m th???t h???p l?? nha.");
                    p.sendAddchatYellow("T???y ??i???m ti???m n??ng th??nh c??ng");
                } else if (b3 == 1) {
                    if (p.c.get().countTayKyNang < 1) {
                        Service.chatNPC(p, (short)npcid, "S??? l???n t???y ??i???m k??? n??ng c???a con ???? h???t.");
                        return;
                    }
                    p.restSpoint();
                    --p.c.get().countTayKyNang;
                    Service.chatNPC(p, (short)npcid, "Ta ???? gi??p con t???y ??i???m k??? n??ng, h??y n??ng ??i???m th???t h???p l?? nha.");
                    p.sendAddchatYellow("T???y ??i???m k??? n??ng th??nh c??ng");
                }
                break;
            case 3:
                Service.chatNPC(p, (short)npcid, "Tr?????ng ta l?? 1 ng??i tr?????ng danh gi??, ch??? gi??nh cho nh??ng ninja t??nh n??ng nh?? kem m?? th??i.");
                break;
            case 4:
                Service.chatNPC(p, (short)npcid, "Ta ??ang h??i m???t x??u, ta s??? giao chi???n v???i con sau nha! Bye bye...");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcOokamesama(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:

                switch(b3) {
                    case 0:

                        Server.manager.sendTB(p, "Top ?????i gia y??n", Rank.getStringBXH(0));
                        return;
                    case 1:

                        Server.manager.sendTB(p, "Top cao th???", Rank.getStringBXH(1));
                        return;
                    case 2:

                        Server.manager.sendTB(p, "Top gia t???c", Rank.getStringBXH(2));
                        return;
                    case 3:

                        Server.manager.sendTB(p, "Top hang ?????ng", Rank.getStringBXH(3));
                        return;
                    default:
                        return;
                }
            case 1:
                if (p.c.get().nclass > 0) {
                    Service.chatNPC(p, (short)npcid, "Con ???? v??o l???p t??? tr?????c r???i m??.");
                } else if (p.c.get().ItemBody[1] != null) {
                    Service.chatNPC(p, (short)npcid, "Con c???n c?? 1 t??m h???n trong tr???ng m???i c?? th??? nh???p h???c, h??y th??o v?? kh?? tr??n ng?????i ra!");
                } else if (p.c.getBagNull() < 2) {
                    Service.chatNPC(p, (short)npcid, "H??nh trang c???n ph???i c?? ??t nh???t 2 ?? tr???ng m???i c?? th??? nh???p h???c!");
                } else {
                    if (b3 == 0) {
                        Admission.Admission(p,(byte)3);
                    } else {
                        if (b3 != 1) {
                            Service.chatNPC(p, (short)npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                            break;
                        }

                        Admission.Admission(p,(byte)4);
                    }

                    Service.chatNPC(p, (short)npcid, "H??y ch??m ch??? luy???n t???p, c?? l??m th?? m???i c?? ??n con nh??.");
                }
                break;
            case 2:
                if (p.c.get().nclass != 3 && p.c.get().nclass != 4) {
                    Service.chatNPC(p, (short)npcid, "Con kh??ng ph???i h???c sinh c???a tr?????ng n??y, ta kh??ng th??? gi??p con t???y ??i???m d?????c r???i.");
                } else if (b3 == 0) {
                    if (p.c.get().countTayTiemNang < 1) {
                        Service.chatNPC(p, (short)npcid, "S??? l???n t???y ??i???m k??? n??ng c???a con ???? h???t.");
                        return;
                    }
                    p.restPpoint();
                    --p.c.get().countTayTiemNang;
                    Service.chatNPC(p, (short)npcid, "Ta ???? gi??p con t???y ??i???m ti???m n??ng, h??y n??ng ??i???m th???t h???p l?? nha.");
                    p.sendAddchatYellow("T???y ??i???m ti???m n??ng th??nh c??ng");
                } else if (b3 == 1) {
                    if (p.c.get().countTayKyNang < 1) {
                        Service.chatNPC(p, (short)npcid, "S??? l???n t???y ??i???m k??? n??ng c???a con ???? h???t.");
                        return;
                    }

                    p.restSpoint();
                    --p.c.get().countTayKyNang;
                    Service.chatNPC(p, (short)npcid, "Ta ???? gi??p con t???y ??i???m k??? n??ng, h??y n??ng ??i???m th???t h???p l?? nha.");
                    p.sendAddchatYellow("T???y ??i???m k??? n??ng th??nh c??ng");
                }
                break;
            case 3:
                Service.chatNPC(p, (short)npcid, "Sao h??m nay tr???i n??ng th??? nh???, h??nh nh?? bi???n ?????i kh?? h???u l??m tan h???t b??ng tr?????ng ta r???i!");
                break;
            case 4:
                Service.chatNPC(p, (short)npcid, "Ta ??ang h??i m???t x??u, ta s??? giao chi???n v???i con sau nha! Bye bye...");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcKazeto(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
                switch(b3) {
                    case 0:

                        Server.manager.sendTB(p, "Top ?????i gia y??n", Rank.getStringBXH(0));
                        return;
                    case 1:

                        Server.manager.sendTB(p, "Top cao th???", Rank.getStringBXH(1));
                        return;
                    case 2:

                        Server.manager.sendTB(p, "Top gia t???c", Rank.getStringBXH(2));
                        return;
                    case 3:

                        Server.manager.sendTB(p, "Top hang ?????ng", Rank.getStringBXH(3));
                        return;
                    default:
                        return;
                }
            case 1:
                if (p.c.get().nclass > 0) {
                    Service.chatNPC(p, (short)npcid, "Con ???? v??o l???p t??? tr?????c r???i m??.");
                } else if (p.c.get().ItemBody[1] != null) {
                    Service.chatNPC(p, (short)npcid, "Con c???n c?? 1 t??m h???n trong tr???ng m???i c?? th??? nh???p h???c, h??y th??o v?? kh?? tr??n ng?????i ra!");
                } else if (p.c.getBagNull() < 2) {
                    Service.chatNPC(p, (short)npcid, "H??nh trang c???n ph???i c?? ??t nh???t 2 ?? tr???ng m???i c?? th??? nh???p h???c!");
                } else {
                    if (b3 == 0) {
                        Admission.Admission(p,(byte)5);
                    } else if (b3 == 1) {
                        Admission.Admission(p,(byte)6);
                    }

                    Service.chatNPC(p, (short)npcid, "H??y ch??m ch??? luy???n t???p, c?? l??m th?? m???i c?? ??n con nh??.");
                }
                break;
            case 2:
                if (p.c.get().nclass != 5 && p.c.get().nclass != 6) {
                    Service.chatNPC(p, (short)npcid, "Con kh??ng ph???i h???c sinh c???a tr?????ng n??y, ta kh??ng th??? gi??p con t???y ??i???m d?????c r???i.");
                } else if (b3 == 0) {
                    if (p.c.get().countTayTiemNang < 1) {
                        Service.chatNPC(p, (short)npcid, "S??? l???n t???y ??i???m ti???m n??ng c???a con ???? h???t.");
                        return;
                    }
                    p.restPpoint();
                    --p.c.get().countTayTiemNang;
                    Service.chatNPC(p, (short)npcid, "Ta ???? gi??p con t???y ??i???m ti???m n??ng, h??y n??ng ??i???m th???t h???p l?? nha.");
                    p.sendAddchatYellow("T???y ??i???m ti???m n??ng th??nh c??ng");
                } else if (b3 == 1) {
                    if (p.c.get().countTayKyNang < 1) {
                        Service.chatNPC(p, (short)npcid, "S??? l???n t???y ??i???m k??? n??ng c???a con ???? h???t.");
                        return;
                    }
                    p.restSpoint();
                    --p.c.get().countTayKyNang;
                    Service.chatNPC(p, (short)npcid, "Ta ???? gi??p con t???y ??i???m k??? n??ng, h??y n??ng ??i???m th???t h???p l?? nha.");
                    p.sendAddchatYellow("T???y ??i???m k??? n??ng th??nh c??ng");
                }
                break;
            case 3:
                Service.chatNPC(p, (short)npcid, "Ng????i l?? ng?????i th???i tan b??ng c???a tr?????ng Ookaza v?? mang kem v??? cho tr?????ng Hirosaki ????ng kh??ng?");
                break;
            case 4:
                Service.chatNPC(p, (short)npcid, "Ta ??ang h??i m???t x??u, ta s??? giao chi???n v???i con sau nha! Bye bye...");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcTajima(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
                Service.chatNPC(p, (short)npcid, "Ch??o m???ng con ?????n v???i ng??i l??ng ??i ????u c??ng ph???i nh??? v???!");
                break;
            case 1:
                Service.chatNPC(p, (short)npcid, "Ch???c n??ng Hu??? v???t ph???m v?? nhi???m v??? ??ang c???p nh???t!");
                break;
            case 2:
                if (p.c.timeRemoveClone > System.currentTimeMillis()) {
                    p.toNhanBan();
                } else {
                    Service.chatNPC(p, (short)npcid, "Con kh??ng c?? ph??n th??n ????? s??? d???ng ch???c n??ng n??y!");
                }
                break;
            case 3:
                if (!p.c.isNhanban) {
                    Service.chatNPC(p, (short)npcid, "Con kh??ng ph???i ph??n th??n ????? s??? d???ng ch???c n??ng n??y!");
                    return;
                }
                if (!p.c.clone.isDie && p.c.timeRemoveClone > System.currentTimeMillis()) {
                    p.exitNhanBan(true);
                }
                break;
            case 4:
            case 5:
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcRei(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
                Service.chatNPC(p, (short)npcid, "Ng????i ?????n ????y l??m g??, kh??ng c?? nhi???m v??? cho ng????i ????u!");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcKirin(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
                Service.chatNPC(p, (short)npcid, "Ng????i ?????n ????y l??m g??, kh??ng c?? nhi???m v??? cho ng????i ????u!");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcSoba(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
                Service.chatNPC(p, (short)npcid, "Ta s??? s???m c?? nhi???m v??? cho con!");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcSunoo(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
                Service.chatNPC(p, (short)npcid, "Kh??? kh???...");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcGuriin(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
    }

    public static void npcMatsurugi(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
    }

     public static void npcOkanechan(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
                Server.manager.sendTB(p, "H?????ng d???n", "- ????? n???p ti???n ho???c mua ?????, con h??y l??n Website ho???c THAM GIA BOX ZALO c???a game ????? n???p nh??!");
                break;
            case 1:
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                switch(b3) {
                    case 0:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.level >= 10 && p.c.checkLevel[0] == 0) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(223, true));
                            if(p.status == 1) {
                                p.upluongMessage(1000L);
                                p.c.luongTN += 1000;
                            } else {
                                p.upluongMessage(2000L);
                            }

                            p.c.checkLevel[0] = 1;
                            Service.chatNPC(p, (short)npcid, "Ch??c m???ng con ???? ?????t ?????n c???p ????? m???i!");
                        } else {
                            Service.chatNPC(p, (short)npcid, "Tr??nh ????? c???a con kh??ng ????? ho???c con ???? nh???n th?????ng r???i!");
                        }

                        return;
                    case 1:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.level >= 20 && p.c.checkLevel[1] == 0) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(224, true));
                            if(p.status == 1) {
                                p.upluongMessage(1000L);
                                p.c.luongTN += 1000;
                            } else {
                                p.upluongMessage(2000L);
                            }
                            p.c.checkLevel[1] = 1;
                            Service.chatNPC(p, (short)npcid, "Ch??c m???ng con ???? ?????t ?????n c???p ????? m???i!");
                        } else {
                            Service.chatNPC(p, (short)npcid, "Tr??nh ????? c???a con kh??ng ????? ho???c con ???? nh???n th?????ng r???i!");
                        }

                        return;
                    case 2:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.level >= 30 && p.c.checkLevel[2] == 0) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(225, true));
                            if(p.status == 1) {
                                p.upluongMessage(1000L);
                                p.c.luongTN += 1000;
                            } else {
                                p.upluongMessage(2000L);
                            }
                            p.c.checkLevel[2] = 1;
                            Service.chatNPC(p, (short)npcid, "Ch??c m???ng con ???? ?????t ?????n c???p ????? m???i!");
                        } else {
                            Service.chatNPC(p, (short)npcid, "Tr??nh ????? c???a con kh??ng ????? ho???c con ???? nh???n th?????ng r???i!");
                        }
                        return;
                    case 3:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.level >= 40 && p.c.checkLevel[3] == 0) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(226, true));
                            if(p.status == 1) {
                                p.upluongMessage(1000L);
                                p.c.luongTN += 1000;
                            } else {
                                p.upluongMessage(2000L);
                            }
                            p.c.checkLevel[3] = 1;
                            Service.chatNPC(p, (short)npcid, "Ch??c m???ng con ???? ?????t ?????n c???p ????? m???i!");
                        } else {
                            Service.chatNPC(p, (short)npcid, "Tr??nh ????? c???a con kh??ng ????? ho???c con ???? nh???n th?????ng r???i!");
                        }

                        return;
                    case 4:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.level >= 50 && p.c.checkLevel[4] == 0) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(227, true));
                            if(p.status == 1) {
                                p.upluongMessage(1500L);
                                p.c.luongTN += 1500;
                            } else {
                                p.upluongMessage(3000L);
                            }
                            p.c.checkLevel[4] = 1;
                            Service.chatNPC(p, (short)npcid, "Ch??c m???ng con ???? ?????t ?????n c???p ????? m???i!");
                        } else {
                            Service.chatNPC(p, (short)npcid, "Tr??nh ????? c???a con kh??ng ????? ho???c con ???? nh???n th?????ng r???i!");
                        }

                        return;
                    case 5:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.getBagNull() < 1) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.level >= 70 && p.c.checkLevel[5] == 0) {
                            p.c.addItemBag(false, ItemTemplate.itemDefault(228, true));
                            if(p.status == 1) {
                                p.upluongMessage(1500L);
                                p.c.luongTN += 1500;
                            } else {
                                p.upluongMessage(3000L);
                            }
                            p.c.checkLevel[5] = 1;
                            Service.chatNPC(p, (short)npcid, "Ch??c m???ng con ???? ?????t ?????n c???p ????? m???i!");
                        } else {
                            Service.chatNPC(p, (short)npcid, "Tr??nh ????? c???a con kh??ng ????? ho???c con ???? nh???n th?????ng r???i!");
                        }

                        return;
                    case 6:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.level >= 90 && p.c.checkLevel[6] == 0) {
                            if(p.status == 1) {
                                p.upluongMessage(2500L);
                                p.c.luongTN += 2500;
                            } else {
                                p.upluongMessage(5000L);
                            }
                            p.c.checkLevel[6] = 1;
                            Service.chatNPC(p, (short)npcid, "Ch??c m???ng con ???? ?????t ?????n c???p ????? m???i!");
                        } else {
                            Service.chatNPC(p, (short)npcid, "Tr??nh ????? c???a con kh??ng ????? ho???c con ???? nh???n th?????ng r???i!");
                        }

                        return;
                    case 7:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, "Ch???c n??ng n??y kh??ng d??nh cho ph??n th??n!");
                            return;
                        }

                        if (p.c.level >= 110 && p.c.checkLevel[7] == 0) {
                            if(p.status == 1) {
                                p.upluongMessage(2500L);
                                p.c.luongTN += 2500;
                            } else {
                                p.upluongMessage(5000L);
                            }
                            p.c.checkLevel[7] = 1;
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                        } else {
                            Service.chatNPC(p, (short)npcid, "Tr??nh ????? c???a con kh??ng ????? ho???c con ???? nh???n th?????ng r???i!");
                        }

                        return;
                    case 8:
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.level >= 130 && p.c.checkLevel[8] == 0) {
                            if(p.status == 1) {
                                p.upluongMessage(3500L);
                                p.c.luongTN += 3500;
                            } else {
                                p.upluongMessage(7000L);
                            }
                            p.c.checkLevel[8] = 1;
                            Service.chatNPC(p, (short)npcid, "Ch??c m???ng con ???? ?????t ?????n c???p ????? m???i!");
                        } else {
                            Service.chatNPC(p, (short)npcid, "Tr??nh ????? c???a con kh??ng ????? ho???c con ???? nh???n th?????ng r???i!");
                        }

                        return;
                    default: {
                        break;
                    }
                }
                break;
              case 2: {
                switch (b3) {
                    case 0:{
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if (p.c.yen < 1000000000) {
                            Service.chatNPC(p, (short) npcid, "M??y c???n ph???i c?? tr??n 1.000.000.000 y??n m???i ?????i ???????c");
                        } else {
                            p.c.upyenMessage(-1000000000);
                            p.luongMessage(2000);
                            Service.chatNPC(p, (short) npcid, "?????i y??n sang l?????ng th??nh c??ng");
                        }
                    }
                    break;
                    case 1:{    
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if (p.c.yen < 1000000000) {
                            Service.chatNPC(p, (short) npcid, "M??y c???n ph???i c?? tr??n 1.000.000.000 y??n m???i ?????i ???????c");
                        } else {
                            p.c.upyenMessage(-1000000000);
                            p.c.upxuMessage(50000000);
                            Service.chatNPC(p, (short) npcid, "?????i y??n sang xu th??nh c??ng");
                        }
                    }
                    break;
                    case 2:{
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if (p.luong < 20000) {
                            Service.chatNPC(p, (short) npcid, "M??y c???n ph???i c?? tr??n 20.000 m???i ?????i ???????c");
                        } else {
                            p.luongMessage(-20000);
                            p.c.upxuMessage(700000000);
                            Service.chatNPC(p, (short) npcid, "?????i l?????ng sang xu th??nh c??ng");
                        }
                    }
                    break;
                }
            }
            break;
            case 3:
                Service.chatNPC(p, (short)npcid, "H??y r??n luy???n th???t ch??m ch??? r???i quay l???i ch??? ta nh???n th?????ng nha!");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
     }
     public static void npcCLXTXu(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                switch (b3) {
                    case 0: {
                        Service.sendInputDialog(p, (short) 46_0_0, "Nh???p s??? l?????ng ?????t (ph???i l?? b???i s??? c???a 10) :");
                        break;
                    }
                    case 1: {
                        Service.sendInputDialog(p, (short) 46_0_1, "Nh???p s??? l?????ng ?????t (ph???i l?? b???i s??? c???a 10) :");
                        break;
                    }
                    case 2: {
                        try {
                            String a = "";
                            int i2 = 1;
                            for (CheckTXXu check: CheckTXXu.checkTXXuArrayList) {
                                a += i2 + ". " + check.name + " - " + check.item + " - " + check.time +".\n";
                                i2++;
                            }
                            Server.manager.sendTB(p, "Soi C???u", a);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            }
            case 1: {
                switch (b3) {
                    case 0: {
                        Service.sendInputDialog(p, (short) 45_1_0, "Nh???p s??? l?????ng ?????t (ph???i l?? b???i s??? c???a 10) :");
                        break;
                    }
                    case 1: {
                        Service.sendInputDialog(p, (short) 45_1_1, "Nh???p s??? l?????ng ?????t (ph???i l?? b???i s??? c???a 10) :");
                        break;
                    }
                    case 2: {
                        try {
                            String a = "";
                            int i2 = 1;
                            for (CheckCLLuong check: CheckCLLuong.checkCLLuongArrayList) {
                                a += i2 + ". " + check.name + " - " + check.item + " - " + check.time +".\n";
                                i2++;
                            }
                            Server.manager.sendTB(p, "Soi C???u", a);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
public static void HUYDAT(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
             case 0:{
                p.passold = "";
                Service.sendInputDialog(p, (short) 10, "Nh???p m???t kh???u c??");
                break;
            }
             case 1: {
                switch (b3) {
                    case 0: {
                        if (p.c.xu < 200000000 ) {
                            Service.chatNPC(p, (short) npcid, "H??nh trang c???a con kh??ng c?? ????? 200tr xu");
                            break;
                        }
                            if (p.c.getBagNull() == 0) {
                            p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng");
                            break;
                        }
                            final Item it = ItemTemplate.itemDefault(247);
                            Manager.chatKTG("Ch??c M???ng ng?????i ch??i " + p.c.name +  " ???? ?????i Th??nh C??ng 1 Th???i B???c");
                            p.c.addItemBag(false, it);
                            p.c.upxuMessage(-200000000);
                        break;
                    }
                    case 1: {
                        if (p.c.xu < 2000000000 ) {
                            Service.chatNPC(p, (short) npcid, "H??nh trang c???a con kh??ng c?? ????? 2 t??? xu");
                            break;
                            } else {
                            Manager.chatKTG("Ch??c M???ng ng?????i ch??i " + p.c.name +  " ???? ?????i Th??nh C??ng 1 l??c 10 Th???i B???c Th???t Dubai");
                            p.c.upxuMessage(-2000000000);
                            Item it = new Item();
                            it.id = 247;
                            it.quantity = 10;
                            it.isLock = false;
                            p.c.addItemBag(false, it);
                            
                            for (byte i = 0; i < 20; i++) {
                                
                            break;
                        }
                    }
                }
                    case 2: {
                        if (p.c.quantityItemyTotal(247) < 1) {
                            Service.chatNPC(p, (short) npcid, "Con C???n c?? 1 Th???i b???c");
                            break;
                        }
                            int tl = Util.nextInt(3);
                            if (tl != 1) {
                                Service.chatNPC(p, (short) npcid, "???? ?????i 1 th???i b???c ra 200tr xu");
                                p.c.removeItemBags(247, 1);
                            p.c.upxuMessage(200000000);
                                break;
                            }
                    }
                    case 3: {
                        if (p.c.quantityItemyTotal(247) < 20) {
                            Service.chatNPC(p, (short) npcid, "Con C???n c?? 20 Th???i b???c");
                            break;
                            } else {
                            Service.chatNPC(p, (short) npcid, "???? G???p Th??nh C??ng Ch??c Con Ch??i Game Vui V???");
                            p.c.removeItemBags(247, 20);
                            Item it = new Item();
                            it.id = 247;
                            it.quantity = 20;
                            it.isLock = false;
                            p.c.addItemBag(false, it);
                            
                            for (byte i = 0; i < 20; i++) {
                                
                            break;
                        }
                    }
                }                           
          }
         }  
             case 2: {
                            Server.manager.sendTB(p, "B???NG GI?? QUY ?????I", "-B???n c?? th??? ?????i\n" + 
                                    "-  ????i 100k coin = 20k momo .\n" +
                                    "- ????i 100k l?????ng = 10k momo.\n" +
                                    "- ??I???M DANH H??NG NG??Y.\n" +
                                    "- ??P L??NG C???.\n" +     
                                    "- CH??I C??C GAME GI???I TR??\n");
                            break;
                        }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
    }
    public static void npcRikudou(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        MapTemplate map;
        MobTemplate mob;
        switch(menuId) {
            case 0: {
                Service.chatNPC(p, (short)npcid, "H??y ch??m ch??? l??n nha.");
                break;
            }
            case 1: {
                switch(b3) {
                    case 0: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.level < 10) {
                            Service.chatNPC(p, (short)npcid, "Con c???n ?????t c???p ????? 10 ????? c?? th??? nh???n nhi???m v???.");
                            return;
                        }

                        if (p.c.isTaskHangNgay != 0) {
                            Service.chatNPC(p, (short)npcid, "Ta ???? giao nhi???m v??? cho con tr?????c ???? r???i");
                            return;
                        }

                        if (p.c.countTaskHangNgay >= 20) {
                            Service.chatNPC(p, (short)npcid, "Con ???? ho??n th??nh h???t nhi???m v??? ng??y h??m nay r???i, ng??y mai h??y quay l???i nha.");
                            return;
                        }

                        mob = Service.getMobIdByLevel(p.c.level);
                        if (mob != null) {
                            map = Service.getMobMapId(mob.id);
                            if (map != null) {
                                p.c.taskHangNgay[0] = 0;
                                p.c.taskHangNgay[1] = 0;
                                p.c.taskHangNgay[2] = Util.nextInt(10, 25);
                                p.c.taskHangNgay[3] = mob.id;
                                p.c.taskHangNgay[4] = map.id;
                                p.c.isTaskHangNgay = 1;
                                p.c.countTaskHangNgay++;
                                Service.getTaskOrder(p.c, (byte)0);
                                Service.chatNPC(p, (short)npcid, "????y l?? nhi???m v??? th??? " + p.c.countTaskHangNgay + "/20 trong ng??y c???a con.");
                            }
                        }
                        break;
                    }

                    case 1: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isTaskHangNgay == 0) {
                            Service.chatNPC(p, (short)npcid, "Con ch??a nh???n nhi???m v??? n??o c???!");
                            return;
                        }

                        p.c.isTaskHangNgay = 0;
                        p.c.countTaskHangNgay--;
                        p.c.taskHangNgay = new int[]{-1, -1, -1, -1, -1, 0, p.c.countTaskHangNgay};
                        Service.clearTaskOrder(p.c, (byte)0);
                        Service.chatNPC(p, (short)npcid, "Con ???? hu??? nhi???m v??? l???n n??y.");
                        break;
                    }

                    case 2: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isTaskHangNgay == 0) {
                            Service.chatNPC(p, (short)npcid, "Con ch??a nh???n nhi???m v??? n??o c???!");
                            return;
                        }

                        if (p.c.getBagNull() == 0) {
                            p.conn.sendMessageLog(Language.NOT_ENOUGH_BAG);
                            return;
                        }

                        if (p.c.taskHangNgay[1] < p.c.taskHangNgay[2]) {
                            Service.chatNPC(p, (short)npcid, "Con ch??a ho??n th??nh nhi???m v??? ta giao!");
                            return;
                        }

                        p.c.isTaskHangNgay = 0;
                        p.c.taskHangNgay = new int[]{-1, -1, -1, -1, -1, 0, p.c.countTaskHangNgay};
                        Service.clearTaskOrder(p.c, (byte)0);
                        long luongUp = Util.nextInt(500, 1000);
                        if(p.vip < 1){
                            p.c.upxuMessage(10000L);
                            p.upluongMessage(luongUp/2);
                        }else {
                            p.c.upxuMessage(100000L);
                            p.upluongMessage(1000L);
                        }
                        if (p.c.pointUydanh < 5000) {
                            p.c.pointUydanh += 2;
                        }
                        if(p.c.countTaskHangNgay % 2 == 0) {
                            Item itemUp = ItemTemplate.itemDefault(p.c.gender == 1 ? 733 : 760, true);
                            itemUp.isLock = true;
                            itemUp.isExpires = false;
                            itemUp.expires = -1L;
                            itemUp.quantity = Util.nextInt(2,3);
                            p.c.addItemBag(true, itemUp);
                        } else {
                            Item itemUp = ItemTemplate.itemDefault(p.c.gender == 1 ? 734 : 761, true);
                            itemUp.isLock = true;
                            itemUp.isExpires = false;
                            itemUp.expires = -1L;
                            itemUp.quantity = Util.nextInt(2,3);
                            p.c.addItemBag(true, itemUp);
                        }

                        Service.chatNPC(p, (short)npcid, "Con h??y nh???n l???y ph???n th?????ng c???a m??nh.");
                        break;
                    }

                    case 3: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.taskHangNgay[4] != -1) {
                            Map ma = Manager.getMapid(p.c.taskHangNgay[4]);
                            int var8;
                            TileMap area;
                            for(var8 = 0; var8 < ma.area.length; ++var8) {
                                area = ma.area[var8];
                                if (area.numplayers < ma.template.maxplayers) {
                                    p.c.tileMap.leave(p);
                                    area.EnterMap0(p.c);
                                    return;
                                }
                            }
                        }
                        Service.chatNPC(p, (short)npcid, "Con ch??a nh???n nhi???m v??? n??o c???!");
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 2: {
                switch(b3) {
                    case 0: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.level < 30) {
                            Service.chatNPC(p, (short)npcid, "Con c???n ?????t c???p ????? 30 ????? c?? th??? nh???n nhi???m v??? t?? th??.");
                            return;
                        }

                        if (p.c.isTaskTaThu != 0) {
                            Service.chatNPC(p, (short)npcid, "Ta ???? giao nhi???m v??? cho con tr?????c ???? r???i");
                            return;
                        }

                        if (p.c.countTaskTaThu >= 2) {
                            Service.chatNPC(p, (short)npcid, "Con ???? ho??n th??nh h???t nhi???m v??? ng??y h??m nay r???i, ng??y mai h??y quay l???i nha.");
                            return;
                        }
                        mob = Service.getMobIdTaThu(p.c.level);
                        if (mob != null) {
                            map = Service.getMobMapIdTaThu(mob.id);
                            if (map != null) {
                                p.c.taskTaThu[0] = 1;
                                p.c.taskTaThu[1] = 0;
                                p.c.taskTaThu[2] = 1;
                                p.c.taskTaThu[3] = mob.id;
                                p.c.taskTaThu[4] = map.id;
                                p.c.isTaskTaThu = 1;
                                ++p.c.countTaskTaThu;
                                Service.getTaskOrder(p.c, (byte)1);
                                Service.chatNPC(p, (short)npcid, "H??y ho??n th??nh nhi???m v??? v?? tr??? v??? ????y nh???n th?????ng.");
                            }
                        }
                        break;
                    }
                    case 1: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isTaskTaThu == 0) {
                            Service.chatNPC(p, (short)npcid, "Con ch??a nh???n nhi???m v??? n??o c???!");
                            return;
                        }

                        Service.clearTaskOrder(p.c, (byte)1);
                        p.c.isTaskTaThu = 0;
                        --p.c.countTaskTaThu;
                        p.c.taskTaThu = new int[]{-1, -1, -1, -1, -1, 0, p.c.countTaskTaThu};
                        Service.chatNPC(p, (short)npcid, "Con ???? hu??? nhi???m v??? l???n n??y.");
                        break;
                    }

                    case 2: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isTaskTaThu == 0) {
                            Service.chatNPC(p, (short)npcid, "Con ch??a nh???n nhi???m v??? n??o c???!");
                            return;
                        }

                        if (p.c.taskTaThu[1] < p.c.taskTaThu[2]) {
                            Service.chatNPC(p, (short)npcid, "Con ch??a ho??n th??nh nhi???m v??? ta giao!");
                            return;
                        }

                        if (p.c.getBagNull() < 2) {
                            Service.chatNPC(p, (short)npcid, "H??nh trang c???a con kh??ng ????? ch??? tr???ng ????? nh???n th?????ng");
                            return;
                        }

                        p.c.isTaskTaThu = 0;
                        p.c.taskTaThu = new int[]{-1, -1, -1, -1, -1, 0, p.c.countTaskTaThu};
                        Service.clearTaskOrder(p.c, (byte)1);
                        if (p.c.pointUydanh < 5000) {
                            p.c.pointUydanh += 3;
                        }
                        Item item = ItemTemplate.itemDefault(251, false);
                        item.quantity = Util.nextInt(3, 4);
                        item.isLock = false;
                        p.c.addItemBag(true, item);

                        if(p.c.countTaskTaThu == 1) {
                            Item itemUp = ItemTemplate.itemDefault(p.c.gender == 1 ? 737 : 764, true);
                            itemUp.isLock = true;
                            itemUp.isExpires = false;
                            itemUp.expires = -1L;
                            itemUp.quantity = Util.nextInt(20,30);
                            p.c.addItemBag(true, itemUp);
                        } else {
                            Item itemUp = ItemTemplate.itemDefault(p.c.gender == 1 ? 738 : 765, true);
                            itemUp.isLock = true;
                            itemUp.isExpires = false;
                            itemUp.expires = -1L;
                            itemUp.quantity = Util.nextInt(20,30);
                            p.c.addItemBag(true, itemUp);
                        }

                        Service.chatNPC(p, (short)npcid, "Con h??y nh???n l???y ph???n th?????ng c???a m??nh.");
                        break;
                    }

                    default: {
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (b3) {
                    case 0: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if(ChienTruong.chienTruong == null) {
                            Service.chatNPC(p, (short)npcid, "Chi???n tr?????ng ch??a ???????c t??? ch???c.");
                            return;
                        }
                        if(ChienTruong.chienTruong != null) {
                            if(ChienTruong.chienTruong30 && (p.c.level < 30 || p.c.level >= 50)) {
                                Service.chatNPC(p, (short)npcid, "B??y gi??? l?? th???i gian chi???n tr?????ng cho c???p ????? t??? 30 ?????n 49. Tr??nh ????? c???a con kh??ng ph?? h???p ????? tham gia.");
                                return;
                            }else if(ChienTruong.chienTruong50 && p.c.level < 50) {
                                Service.chatNPC(p, (short)npcid, "B??y gi??? l?? th???i gian chi???n tr?????ng cho c???p ????? l???n h??n ho???c b???ng 50. Tr??nh ????? c???a con kh??ng ph?? h???p ????? tham gia.");
                                return;
                            }
                            if((ChienTruong.chienTruong30 ||ChienTruong.chienTruong50) && p.c.pheCT == 1) {
                                Service.chatNPC(p, (short)npcid, "Con ???? ??i???m danh phe H???c gi??? tr?????c ???? r???i.");
                                return;
                            }
                            if(ChienTruong.start && p.c.pheCT==-1) {
                                Service.chatNPC(p, (short)npcid, "Chi???n tr?????ng ???? b???t ?????u, kh??ng th??? b??o danh.");
                                return;
                            }
                            if((ChienTruong.chienTruong30 || ChienTruong.chienTruong50) && p.c.pheCT == -1 ) {
                                if (p.c.pointUydanh < 5000) {
                                    p.c.pointUydanh += 10;
                                }
                                p.c.pheCT = 0;
                                p.c.pointCT = 0;
                                p.c.isTakePoint = 0;
                                p.c.typepk = 4;
                                Service.ChangTypePkId(p.c, (byte)4);
                                Service.updatePointCT(p.c, 0);
                                if(p.c.party != null) {
                                    p.c.party.removePlayer(p.c.id);
                                }
                                if(!ChienTruong.bxhCT.containsKey(p.c)) {
                                    ChienTruong.bxhCT.put(p.c, p.c.pointCT);
                                } else {
                                    ChienTruong.bxhCT.replace(p.c, p.c.pointCT);
                                }
                                Map ma = Manager.getMapid(ChienTruong.chienTruong.map[0].id);
                                for (TileMap area : ma.area) {
                                    if (area.numplayers < ma.template.maxplayers) {
                                        p.c.tileMap.leave(p);
                                        area.EnterMap0(p.c);
                                        return;
                                    }
                                }
                                return;
                            }
                            p.c.typepk = 4;
                            Service.ChangTypePkId(p.c, (byte)4);
                            Service.updatePointCT(p.c, 0);
                            if(p.c.party != null) {
                                p.c.party.removePlayer(p.c.id);
                            }
                            if(!ChienTruong.bxhCT.containsKey(p.c)) {
                                ChienTruong.bxhCT.put(p.c, p.c.pointCT);
                            } else {
                                ChienTruong.bxhCT.replace(p.c, p.c.pointCT);
                            }
                            Map ma = Manager.getMapid(ChienTruong.chienTruong.map[0].id);
                            for (TileMap area : ma.area) {
                                if (area.numplayers < ma.template.maxplayers) {
                                    p.c.tileMap.leave(p);
                                    area.EnterMap0(p.c);
                                    return;
                                }
                            }
                        }
                        return;
                    }
                    case 1: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if(ChienTruong.chienTruong == null) {
                            Service.chatNPC(p, (short)npcid, "Chi???n tr?????ng ch??a ???????c t??? ch???c.");
                            return;
                        }
                        if(ChienTruong.chienTruong != null) {
                            if( ChienTruong.chienTruong30 && (p.c.level < 30 || p.c.level >= 50)) {
                                Service.chatNPC(p, (short)npcid, "B??y gi??? l?? th???i gian chi???n tr?????ng cho c???p ????? t??? 30 ?????n 49. Tr??nh ????? c???a con kh??ng ph?? h???p ????? tham gia.");
                                return;
                            }else if(ChienTruong.chienTruong50 && p.c.level < 50) {
                                Service.chatNPC(p, (short)npcid, "B??y gi??? l?? th???i gian chi???n tr?????ng cho c???p ????? l???n h??n ho???c b???ng 50. Tr??nh ????? c???a con kh??ng ph?? h???p ????? tham gia.");
                                return;
                            }
                            if(ChienTruong.start && p.c.pheCT==-1) {
                                Service.chatNPC(p, (short)npcid, "Chi???n tr?????ng ???? b???t ?????u, kh??ng th??? b??o danh.");
                                return;
                            }
                            if((ChienTruong.chienTruong30 ||ChienTruong.chienTruong50) && p.c.pheCT == 0) {
                                Service.chatNPC(p, (short)npcid, "Con ???? ??i???m danh phe B???ch gi??? tr?????c ???? r???i.");
                                return;
                            }
                            if( (ChienTruong.chienTruong30 || ChienTruong.chienTruong50) && p.c.pheCT == -1 ) {
                                if (p.c.pointUydanh < 5000) {
                                    p.c.pointUydanh += 10;
                                }
                                p.c.pheCT = 1;
                                p.c.pointCT = 0;
                                p.c.typepk = 5;
                                p.c.isTakePoint = 0;
                                Service.ChangTypePkId(p.c, (byte)5);
                                Service.updatePointCT(p.c, 0);
                                if(p.c.party != null) {
                                    p.c.party.removePlayer(p.c.id);
                                }
                                if(!ChienTruong.bxhCT.containsKey(p.c)) {
                                    ChienTruong.bxhCT.put(p.c, p.c.pointCT);
                                } else {
                                    ChienTruong.bxhCT.replace(p.c, p.c.pointCT);
                                }
                                Map ma = Manager.getMapid(ChienTruong.chienTruong.map[6].id);
                                for (TileMap area : ma.area) {
                                    if (area.numplayers < ma.template.maxplayers) {
                                        p.c.tileMap.leave(p);
                                        area.EnterMap0(p.c);
                                        return;
                                    }
                                }
                                return;
                            }
                            p.c.typepk = 5;
                            Service.ChangTypePkId(p.c, (byte)5);
                            Service.updatePointCT(p.c, 0);
                            if(p.c.party != null) {
                                p.c.party.removePlayer(p.c.id);
                            }
                            if(!ChienTruong.bxhCT.containsKey(p.c)) {
                                ChienTruong.bxhCT.put(p.c, p.c.pointCT);
                            } else {
                                ChienTruong.bxhCT.replace(p.c, p.c.pointCT);
                            }
                            Map ma = Manager.getMapid(ChienTruong.chienTruong.map[6].id);
                            for (TileMap area : ma.area) {
                                if (area.numplayers < ma.template.maxplayers) {
                                    p.c.tileMap.leave(p);
                                    area.EnterMap0(p.c);
                                    return;
                                }
                            }
                        }
                        return;
                    }
                    case 2: {
                        if(ChienTruong.finish) {
                            Service.evaluateCT(p.c);
                        } else {
                            Server.manager.sendTB(p, "K???t qu???", "Ch??a c?? th??ng tin.");
                        }
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 4: {
                Server.manager.sendTB(p, "H?????ng d???n", "Chi???n tr?????ng ???????c m??? 2 l???n m???i ng??y.\n" +
                        "- Chi???n tr?????ng lv30: gi??nh cho nh??n v???t level t??? 30 ?????n 45, ??i???m danh v??o l??c 19h v?? b???t ?????u t??? 19h30' ?????n 20h30'.\n" +
                        "- Chi???n tr?????ng lv50: gi??nh cho nh??n v???t level t??? 50 tr??? l??n, ??i???m danh v??o l??c 21h v?? b???t ?????u t??? 21h30' ?????n 22h30'.\n\n" +
                        "+ Top1: 10v ??an m???i lo???i + 3tr xu.\n" +
                        "+ Top 2: 7v ??an m???i lo???i + 2tr xu.\n" +
                        "+ Top 3: 5v ??an m???i lo???i + 1tr xu.\n" +
                        "+ Phe th???ng: 1v ??an m???i lo???i + 500k xu.");
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcGoosho(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
                p.requestItem(14);
                break;
            case 1:
                p.requestItem(15);
                break;
            case 2:
                p.requestItem(32);
                break;
            case 3:
                p.requestItem(34);
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcTruCoQuan(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                if(p.c.quantityItemyTotal(260) < 1) {
                    p.sendAddchatYellow("Kh??ng c?? ch??a kho?? ????? m??? c???a n??y.");
                    return;
                }
                if(p.c.tileMap.map.lanhDiaGiaToc != null && p.c.tileMap.map.mapLDGT()) {
                    switch (p.c.tileMap.map.id) {
                        case 80: {

                            p.c.tileMap.map.lanhDiaGiaToc.openMap(1, p);
                            break;
                        }
                        case 81: {

                            p.c.tileMap.map.lanhDiaGiaToc.openMap(2, p);
                            break;
                        }
                        case 82: {

                            p.c.tileMap.map.lanhDiaGiaToc.openMap(3, p);
                            break;
                        }
                        case 83: {

                            p.c.tileMap.map.lanhDiaGiaToc.openMap(4, p);
                            break;
                        }
                        case 84: {

                            p.c.tileMap.map.lanhDiaGiaToc.openMap(5, p);
                            break;
                        }
                        case 85: {
                            p.c.tileMap.map.lanhDiaGiaToc.openMap(6, p);
                            break;
                        }
                        case 86: {
                            p.c.tileMap.map.lanhDiaGiaToc.openMap(7, p);
                            break;
                        }
                        case 87: {
                            p.c.tileMap.map.lanhDiaGiaToc.openMap(8, p);
                            Server.manager.sendTB(p, "Ghi ch??", "Con ???????ng n??y s??? d???n ?????n c??nh c???a n??i ??? c???a m???t nh??n v???t huy???n b?? ???? b??? l???i nguy???n c??? " +
                                    "x??a y???m b??a r???ng s??? kh??ng ai c?? th??? ????nh b???i ???????c nh??n v???t huy???n b?? n??y. B???n h??y mau t??m c??ch ho?? gi???i l???i nguy???n.");
                            break;
                        }
                        case 88: {
                            p.c.tileMap.map.lanhDiaGiaToc.openMap(9, p);
                            Server.manager.sendTB(p, "Ghi ch??", "Con ???????ng n??y s??? d???n ?????n c??nh c???a n??i ??? c???a m???t nh??n v???t huy???n b?? ???? b??? l???i nguy???n c??? " +
                                    "x??a y???m b??a r???ng s??? kh??ng ai c?? th??? ????nh b???i ???????c nh??n v???t huy???n b?? n??y. B???n h??y mau t??m c??ch ho?? gi???i l???i nguy???n.");
                            break;
                        }
                        case 89: {
                            p.c.tileMap.map.lanhDiaGiaToc.openMap(10, p);
                            Server.manager.sendTB(p, "Ghi ch??", "Con ???????ng n??y s??? d???n ?????n c??nh c???a n??i ??? c???a m???t nh??n v???t huy???n b?? ???? b??? l???i nguy???n c??? " +
                                    "x??a y???m b??a r???ng s??? kh??ng ai c?? th??? ????nh b???i ???????c nh??n v???t huy???n b?? n??y. B???n h??y mau t??m c??ch ho?? gi???i l???i nguy???n.");
                            break;
                        }
                        default: {
                            break;
                        }

                    }
                }
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
    }

    public static void npcShinwa(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                p.menuIdAuction = b3;
                final List<ShinwaTemplate> itemShinwas = ShinwaManager.entrys.get((int)b3);
                final Message mess = new Message(103);
                mess.writer().writeByte(b3);
                if(itemShinwas != null) {
                    mess.writer().writeInt(itemShinwas.size());
                    ShinwaTemplate item;
                    for(int i = 0; i < itemShinwas.size(); i++) {
                        item = itemShinwas.get(i);
                        if(item != null) {
                            mess.writer().writeInt(i);
                            mess.writer().writeInt(item.getRemainTime());
                            mess.writer().writeShort(item.getItem().quantity);
                            mess.writer().writeUTF(item.getSeller());
                            mess.writer().writeInt((int)item.getPrice());
                            mess.writer().writeShort(item.getItem().id);
                        } else {
                            mess.writer().writeInt(i);
                            mess.writer().writeInt(-1);
                            mess.writer().writeShort(0);
                            mess.writer().writeUTF("");
                            mess.writer().writeInt(999999999);
                            mess.writer().writeShort(12);
                        }
                    }
                } else {
                    mess.writer().writeInt(0);
                }
                mess.writer().flush();
                p.conn.sendMessage(mess);
                mess.cleanup();
                break;
            }
            case 1: {
                final int itemShinwa = ShinwaManager.entrys.size();
                System.out.println("S??? l?????ng "+ itemShinwa);
                if(itemShinwa > 30000){
                    p.conn.sendMessageLog("Gian h??ng ???? full v???t ph???m");
                    break;
                }
                p.menuIdAuction = -2;
                p.requestItem(36);
                break;
            }
            case 2: {
                try {
                    synchronized (ShinwaManager.entrys.get((int)-1)) {
                        List<ShinwaTemplate> itemShinwas = ShinwaManager.entrys.get((int)-1);
                        System.out.print(itemShinwas.size());
                        List<Integer> ind = new ArrayList<>();
                        ShinwaTemplate item;
                        for(int i = itemShinwas.size() - 1; i>=0; i--) {
                            item = itemShinwas.get(i);
                            if(item != null && item.getSeller().equals(p.c.name)) {
                                if(p.c.getBagNull() == 0) {
                                    Service.chatNPC(p, (short) npcid, "H??nh trang kh??ng ????? ch??? tr???ng ????? nh???n th??m v???t ph???m!");
                                    break;
                                } 
                                p.c.addItemBag(true, item.getItem());
                                ind.add(i);
                                itemShinwas.remove(i);
                            }
                        }                       
                        if(ind.size() < 1) {
                            Service.chatNPC(p, (short) npcid, "Con kh??ng c?? ????? ????? nh???n l???i!");
                            return;
                        }
                        for(int i : ind) {
                            itemShinwas.remove(i);
                        }
                    }
                } catch (Exception e) {
                    p.conn.sendMessageLog("C?? l???i, vui l??ng th??? l???i sau!");
                }
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
    }

    public static void npcChiHang(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
    }

    public static void npcRakkii(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                p.requestItem(38);
                break;
            }
            case 1: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                Service.sendInputDialog(p, (short) 4, "Nh???p Gift Code t???i ????y");
                break;
            }
            case 2: {
                switch (b3) {
                    case 0:
                    case 1: {
                        Server.manager.rotationluck[0].luckMessage(p);
                        return;
                    }
                    case 2: {
                        Server.manager.sendTB(p, "V??ng xoay vip", "H??y ?????t c?????c xu v?? th??? v???n may c???a m??nh trong 2 ph??t nha.");
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            case 3: {
                switch (b3) {
                    case 0:
                    case 1: {
                        Server.manager.rotationluck[1].luckMessage(p);
                        return;
                    }
                    case 2: {
                        Server.manager.sendTB(p, "V??ng xoay th?????ng", "H??y ?????t c?????c xu v?? th??? v???n may c???a m??nh trong 2 ph??t nha.");
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcLongDen(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
    }

    public static void npcKagai(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 1: {
                switch (b3) {
                    case 0: {
                        if (p.c.isNhanban) {
                            p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if (p.c.clan == null) {
                            Service.chatNPC(p, (short) npcid, "Con ch??a c?? Gia t???c.");
                            return;
                        }
                        if (p.c.clan != null && p.c.clan.typeclan != 4) {
                            Service.chatNPC(p, (short) npcid, "Con kh??ng ph???i t???c tr?????ng, kh??ng th??? m???i gia t???c chi???n.");
                            return;
                        }
                        Service.sendInputDialog(p, (short) 5, "Nh???p t??n gia t???c ?????i ph????ng");
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 3: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                } else {
                    Item it;
                    Char var6;
                    switch (b3) {
                        case 0:
                            if (p.c.pointUydanh < 3000000) {
                                Service.chatNPC(p, (short) npcid, "Ch???c n??ng t???m ????ng");
                                return;
                            } else {
                                if (p.c.getBagNull() < 1) {
                                    Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                } else {
                                    var6 = p.c;
                                    var6.pointUydanh -= 300;
                                    it = ItemTemplate.itemDefault(396 + p.c.nclass);
                                    it.isLock = false;
                                    it.quantity = 1;
                                    it.isExpires = true;
                                    it.expires = System.currentTimeMillis() + 259200000L;
                                    p.c.addItemBag(false, it);
                                    p.c.upxuMessage(3000000);
                                }

                                return;
                            }
                        case 1: {
                            if (p.c.pointUydanh < 7099990) {
                                Service.chatNPC(p, (short) npcid, "Ch???c n??ng t???m ????ng");
                                return;
                            } else {
                                if (p.c.getBagNull() < 1) {
                                    Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                } else {
                                    var6 = p.c;
                                    var6.pointUydanh -= 700;
                                    it = ItemTemplate.itemDefault(396 + p.c.nclass);
                                    it.isLock = false;
                                    it.quantity = 1;
                                    it.isExpires = true;
                                    it.expires = System.currentTimeMillis() + 432000000L;
                                    p.c.addItemBag(false, it);
                                    p.c.upxuMessage(5000000);
                                }
                                return;
                            }

                        }
                        case 2: {
                            if (p.c.pointUydanh < 200000000) {
                                Service.chatNPC(p, (short) npcid, "Ch???c n??ng t???m ????ng");
                            } else if (p.c.getBagNull() < 1) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                            } else {
                                var6 = p.c;
                                var6.pointUydanh -= 2000;
                                it = ItemTemplate.itemDefault(396 + p.c.nclass);
                                it.isLock = false;
                                it.quantity = 1;
                                it.isExpires = true;
                                it.expires = System.currentTimeMillis() + 1296000000L;
                                p.c.addItemBag(false, it);
                                p.c.upxuMessage(10000000);
                            }
                            break;
                        }
                    }
                }
                break;
            }
            case 4: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    break;
                } else {
                    switch (b3) {
                        case 0: {
                            p.requestItem(43);
                            break;
                        }
                        case 1: {
                            p.requestItem(44);
                            break;
                        }
                        case 2: {
                            p.requestItem(45);
                            break;
                        }
                        case 3: {
                            Server.manager.sendTB(p, "H?????ng d???n", "- Tinh luy???n...");
                            break;
                        }
                        default: {
                            Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                            break;
                        }
                    }
                }
                break;
            }
            case 0:
            case 2:
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcTienNu(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        if (p.typemenu == 33) {
            Item it;
            switch(Server.manager.event) {
                //H??
                case 1: {
                    if (p.c.isNhanban) {
                        Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    } else {
                        switch (menuId) {
                            case 0: {
                                if (p.c.quantityItemyTotal(432) >= 1 && p.c.quantityItemyTotal(428) >= 3 && p.c.quantityItemyTotal(429) >= 2 && p.c.quantityItemyTotal(430) >= 3) {
                                    if (p.c.getBagNull() == 0) {
                                        p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng");
                                    } else {
                                        it = ItemTemplate.itemDefault(434);
                                        p.c.addItemBag(true, it);
                                        p.c.removeItemBags(432, 1);
                                        p.c.removeItemBags(428, 3);
                                        p.c.removeItemBags(429, 2);
                                        p.c.removeItemBags(430, 3);
                                    }

                                    return;
                                } else {
                                    Service.chatNPC(p, (short) npcid, "H??nh trang c???a con kh??ng c?? ????? nguy??n li???u");
                                }
                                break;
                            }
                            case 1: {
                                if (p.c.quantityItemyTotal(433) >= 1 && p.c.quantityItemyTotal(428) >= 2 && p.c.quantityItemyTotal(429) >= 3 && p.c.quantityItemyTotal(431) >= 2) {
                                    if (p.c.getBagNull() == 0) {
                                        p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng");
                                    } else {
                                        it = ItemTemplate.itemDefault(435);
                                        p.c.addItemBag(true, it);
                                        p.c.removeItemBags(433, 1);
                                        p.c.removeItemBags(428, 2);
                                        p.c.removeItemBags(429, 3);
                                        p.c.removeItemBags(431, 2);
                                    }
                                } else {
                                    Service.chatNPC(p, (short) npcid, "H??nh trang c???a con kh??ng c?? ????? nguy??n li???u");
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
                //Trung thu
                case 2: {
                    if (p.c.isNhanban) {
                        Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    } else {
                        switch (menuId) {
                            case 0: {
                                if (p.c.quantityItemyTotal(304) >= 1 && p.c.quantityItemyTotal(298) >= 1 && p.c.quantityItemyTotal(299) >= 1 && p.c.quantityItemyTotal(300) >= 1 && p.c.quantityItemyTotal(301) >= 1) {
                                    if (p.c.getBagNull() == 0) {
                                        p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng");
                                    } else {
                                        it = ItemTemplate.itemDefault(302);
                                        p.c.addItemBag(true, it);
                                        p.c.removeItemBags(304, 1);
                                        p.c.removeItemBags(298, 1);
                                        p.c.removeItemBags(299, 1);
                                        p.c.removeItemBags(300, 1);
                                        p.c.removeItemBags(301, 1);
                                    }

                                    return;
                                } else {
                                    Service.chatNPC(p, (short) npcid, "H??nh trang c???a con kh??ng c?? ????? nguy??n li???u");
                                }
                                break;
                            }
                            case 1: {
                                if (p.c.quantityItemyTotal(305) >= 1 && p.c.quantityItemyTotal(298) >= 1 && p.c.quantityItemyTotal(299) >= 1 && p.c.quantityItemyTotal(300) >= 1 && p.c.quantityItemyTotal(301) >= 1) {
                                    if (p.c.getBagNull() == 0) {
                                        p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng");
                                    } else {
                                        it = ItemTemplate.itemDefault(303);
                                        p.c.addItemBag(true, it);
                                        p.c.removeItemBags(305, 1);
                                        p.c.removeItemBags(298, 1);
                                        p.c.removeItemBags(299, 1);
                                        p.c.removeItemBags(300, 1);
                                        p.c.removeItemBags(301, 1);
                                    }

                                    return;
                                } else {
                                    Service.chatNPC(p, (short) npcid, "H??nh trang c???a con kh??ng c?? ????? nguy??n li???u");
                                }
                                break;
                            }
                            case 2: {
                                if (p.c.yen >= 10000 && p.c.quantityItemyTotal(292) >= 3 && p.c.quantityItemyTotal(293) >= 2 && p.c.quantityItemyTotal(294) >= 3) {
                                    if (p.c.getBagNull() == 0) {
                                        p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng");
                                    } else {
                                        it = ItemTemplate.itemDefault(298);
                                        p.c.addItemBag(true, it);
                                        p.c.upyenMessage(-10000L);
                                        p.c.removeItemBags(292, 3);
                                        p.c.removeItemBags(293, 2);
                                        p.c.removeItemBags(294, 3);
                                    }

                                    return;
                                } else {
                                    Service.chatNPC(p, (short) npcid, "H??nh trang c???a con kh??ng c?? ????? nguy??n li???u");
                                }
                                break;
                            }
                            case 3: {
                                if (p.c.yen >= 10000 && p.c.quantityItemyTotal(292) >= 2 && p.c.quantityItemyTotal(295) >= 3 && p.c.quantityItemyTotal(294) >= 2) {
                                    if (p.c.getBagNull() == 0) {
                                        p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng");
                                    } else {
                                        it = ItemTemplate.itemDefault(299);
                                        p.c.addItemBag(true, it);
                                        p.c.upyenMessage(-10000L);
                                        p.c.removeItemBags(292, 2);
                                        p.c.removeItemBags(295, 3);
                                        p.c.removeItemBags(294, 2);
                                    }

                                    return;
                                } else {
                                    Service.chatNPC(p, (short) npcid, "H??nh trang c???a con kh??ng c?? ????? nguy??n li???u");
                                }
                                break;
                            }
                            case 4: {
                                if (p.c.yen >= 10000 && p.c.quantityItemyTotal(292) >= 2 && p.c.quantityItemyTotal(295) >= 3 && p.c.quantityItemyTotal(297) >= 3) {
                                    if (p.c.getBagNull() == 0) {
                                        p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng");
                                    } else {
                                        it = ItemTemplate.itemDefault(300);
                                        p.c.addItemBag(true, it);
                                        p.c.upyenMessage(-10000L);
                                        p.c.removeItemBags(292, 2);
                                        p.c.removeItemBags(295, 3);
                                        p.c.removeItemBags(297, 3);
                                    }

                                    return;
                                } else {
                                    Service.chatNPC(p, (short) npcid, "H??nh trang c???a con kh??ng c?? ????? nguy??n li???u");
                                }
                                break;
                            }
                            case 5: {
                                if (p.c.yen >= 10000 && p.c.quantityItemyTotal(292) >= 2 && p.c.quantityItemyTotal(296) >= 2 && p.c.quantityItemyTotal(297) >= 3) {
                                    if (p.c.getBagNull() == 0) {
                                        p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng");
                                    } else {
                                        it = ItemTemplate.itemDefault(301);
                                        p.c.addItemBag(true, it);
                                        p.c.upyenMessage(-10000L);
                                        p.c.removeItemBags(292, 2);
                                        p.c.removeItemBags(296, 2);
                                        p.c.removeItemBags(297, 3);
                                    }
                                } else {
                                    Service.chatNPC(p, (short) npcid, "H??nh trang c???a con kh??ng c?? ????? nguy??n li???u");
                                }
                                break;
                            }
                        }
                    }
                    break;
                }

                //Noel
                case 3: {
                    if (p.c.isNhanban) {
                        Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                        return;
                    }
                    switch (menuId) {
                        case 0: {
                            Service.sendInputDialog(p, (short) 6, "Nh???p s??? l?????ng b??nh Chocolate mu???n l??m.");
                            break;
                        }
                        case 1: {
                            Service.sendInputDialog(p, (short) 7, "Nh???p s??? l?????ng b??nh  D??u t??y mu???n l??m.");
                            break;
                        }
                        case 2: {
                            if(p.c.pointNoel < 3500) {
                                Service.chatNPC(p, (short) npcid, "Con c???n ??t nh???t 3500 ??i???m ????? ?????i m???t n??? 7 ng??y.");
                                return;
                            }
                            p.c.pointNoel -= 3500;
                            it = ItemTemplate.itemDefault(p.c.gender == 1 ? 407 : 408);
                            it.isLock = false;
                            it.quantity = 1;
                            it.isExpires = true;
                            it.expires = System.currentTimeMillis() + 604800000L;
                            p.c.addItemBag(false, it);
                            break;
                        }
                        case 3: {
                            if(p.c.pointNoel < 5000) {
                                Service.chatNPC(p, (short) npcid, "Con c???n ??t nh???t 5000 ??i???m ????? ?????i pet Ho??? long 7 ng??y.");
                                return;
                            }
                            p.c.pointNoel -= 5000;
                            it = ItemTemplate.itemDefault(583);
                            it.isLock = false;
                            it.quantity = 1;
                            it.isExpires = true;
                            it.expires = System.currentTimeMillis() + 604800000L;
                            p.c.addItemBag(false, it);
                            break;
                        }
                        case 4: {
                            String a = "";
                            if(Rank.bxhBossTuanLoc.isEmpty()) {
                                a = "Ch??a c?? th??ng tin.";
                            }
                            for(Rank.Entry3 item : Rank.bxhBossTuanLoc) {
                                a += item.index +". "+item.name+": "+item.point+" ??i???m\n";
                            }
                            Server.manager.sendTB(p, "BXH Di???t Boss", a);
                            break;
                        }
                        case 5: {
                            Server.manager.sendTB(p, "H?????ng d???n", "- S??? ??i???m hi???n t???i c???a b???n l??: "+p.c.pointNoel+"\n" +
                                    "- Ki???m ??i???m s??? ki???n b???ng c??ch nh???n qu?? h??ng ng??y t???i C??y th??ng (+1 ??i???m), trang tr?? c??y th??ng (+10 ??i???m), gi???t boss Tu???n l???c (+1 ??i???m).\n" +
                                    "- D??ng ??i???m ????? d???i l???y v???t ph???m qu?? gi??: M???t n??? Super Broly/Onna Bugeisha 7 ng??y (3500 ??i???m), Pet Ho??? long 7 ng??y (5000 ??i???m).\n" +
                                    "- B??nh Chocolate: 2 B?? + 2 Kem + 3 ???????ng + 1 Chocolate + 5000 y??n.\n" +
                                    "- B??nh D??u t??y: 3 B?? + 3 Kem + 4 ???????ng + 1 D??u t??y + 10000 y??n.\n");
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                //T???t
                case 4: {
                    if (p.c.isNhanban) {
                        Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                        return;
                    }
                    switch (menuId) {
                        case 0: {
                            Service.sendInputDialog(p, (short) 110, "Nh???p s??? l?????ng b??nh Ch??ng mu???n l??m :");
                            break;
                        }
                        case 1: {
                            Service.sendInputDialog(p, (short) 111, "Nh???p s??? l?????ng b??nh T??t mu???n l??m :");
                            break;
                        }
                        case 2: {
                            if(p.luong < 500){
                                p.conn.sendMessageLog("B???n kh??ng ????? 500 l?????ng ????? th???c hi???n ??i???u n??y.");
                                return;
                                }else {
                                   Service.sendInputDialog(p, (short) 113, "Nh???p t??n nh??n v???t nh???n:");
                                }
                            break;
                        }
                        case 3: 
                        {
                            Service.sendInputDialog(p, (short) 112, "Nh???p s??? l?????ng Ph??o mu???n l??m :");
                            break;
                        }
                        case 4: {
                            String a = "";
                            if(Rank.bxhBossChuot.isEmpty()) {
                                a = "Ch??a c?? th??ng tin.";
                            }
                            for(Rank.Entry4 item : Rank.bxhBossChuot) {
                                a += item.index +". "+item.name+": "+item.point1+" ??i???m\n";
                            }
                            Server.manager.sendTB(p, "BXH Di???t Boss Chu???t", a);
                            break;
                        }
                        case 5: {
                            Server.manager.sendTB(p, "H?????ng d???n", "----------------- L??m B??nh Ch??ng -----------------\n +, 3 l?? dong + 5 n???p + 1 th???t heo + 3 ?????u xanh + 2 l???t tre + 50.000 xu + 50.000 y??n.\n" +
                                    "----------------- L??m B??nh T??t -----------------\n +,  2 l?? dong + 4 n???p + 2 ?????u xanh + 4 l???t tre + 40.000 xu + 40.000 y??n.\n" +
                                    "----------------- L??m Ph??o -----------------\n +, Gh??p 10 m???nh Ph??o + 30k xu + 30k y??n th??nh 1 d??y Ph??o, s??? d???ng v?? nh???n qu?? b???t ng???.\n -B???n c?? th??? mua t???i Goosho ho???c tham gia L??i ????i ????? nh???n M???nh Ph??o ( c??? chi???n th???ng 5 tr???n c???ng d???n b???n s??? nh???n ???????c 1 m???nh Ph??o ).\n" +
                                    "----------------- S??n Boss S??? Ki???n -----------------\n +, Trong qu?? tr??nh di???n ra s??? ki???n T???t ,Boss Chu???t s??? xu???t hi???n ng???u nhi??n t???i c???nh c??c Tr?????ng ,h??y nhanh tay ti??u di???t ch??ng v?? nh???n ??i???m ( c??? gi???t 1 em b???n s??? nh???n ???????c 1 ??i???m ) ????? ?????i nh???ng Ph???n Qu?? c?? Gi?? Tr??? nh??.\n");
                                    
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                // 8/3
                case 5: {
                    if (p.c.isNhanban) {
                        Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                        return;
                    }
                    switch (menuId) {
                        case 0:
                            Service.sendInputDialog(p, (short) 119, "Nh???p s??? hoa mu???n l??m");
                            break;
                        
                        case 1: 
                            Service.sendInputDialog(p, (short) 120, "Nh???p s??? hoa mu???n l??m");
                            break;
                        
                        case 2: 
                            Service.sendInputDialog(p, (short) 121, "Nh???p s??? hoa mu???n l??mt");
                            break;
                        
               
                        case 3: 
                            Service.sendInputDialog(p, (short) 122, "Nh???p s??? hoa mu???n l??m");
                            break;
                  
                            case 4: 
                                Service.sendInputDialog(p, (short) 123, "Nh???p t??n nh??n v???t");
                            break;
                            
                            case 5: 
                                Service.sendInputDialog(p, (short) 124, "Nh???p t??n nh??n v???t");
                            break;
                             
                            case 6: 
                                Service.sendInputDialog(p, (short) 125, "Nh???p t??n nh??n v???t");
                            break;
                             
                            case 7: 
                                Service.sendInputDialog(p, (short) 118, "Nh???p t??n nh??n v???t");
                            break;
                             
                       
                           case 8: {
                                Server.manager.sendTB(p, "H?????ng d???n", "C??ch gh??p hoa: \n  - B?? hoa h???ng ????? = 8 Hoa h???ng ????? + 1 Gi???y m??u + 1 Ruy b??ng + 1 Khung tre\n" +
                                    " - B?? hoa h???ng v??ng = 8 Hoa h???ng v??ng + 1 Gi???y m??u + 1 Ruy b??ng + 1 Khung tre\n" +
                                    " - B?? hoa h???ng xanh = 8 Hoa h???ng xanh + 1 Gi???y m??u + 1 Ruy b??ng + 1 Khung tre\n" +
                                    " - Gi??? hoa = 8 Hoa h???ng ????? + 8 Hoa h???ng v??ng + 8 Hoa h???ng xanh + 1 Gi???y m??u + 1 Ruy b??ng + 1 Khung tre\n");
                           }
                    }
                }
                default: {
                    Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                    break;
                }
            }

        }
    }

    public static void npcCayThong(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                if (p.c.isNhanban) {
                    p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if(p.c.level < 40) {
                    p.conn.sendMessageLog("Nh??n v???t ph???i tr??n level 40 m???i c?? th??? nh???n qu?? v?? trang tr??.");
                    return;
                }
                if(p.c.isNhanQuaNoel < 1) {
                    p.conn.sendMessageLog("H??m nay b???n ???? nh???n qu?? r???i.");
                    return;
                }
                if(p.c.getBagNull() < 1) {
                    p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng ????? nh???n qu??");
                    return;
                }
                p.c.isNhanQuaNoel = 0;
                p.c.pointNoel++;
                int random = Util.nextInt(0,2);
                switch (random) {
                    case 0: {
                        int yen = Util.nextInt(500000,1000000);
                        if(p.status == 1) {
                            yen /= 2;
                            p.c.yenTN += yen;
                        }
                        p.c.upyenMessage(yen);
                        p.sendAddchatYellow("B???n nh???n ???????c " + yen + " y??n.");
                        break;
                    }
                    case 1: {
                        int xu = Util.nextInt(100000,300000);
                        if(p.status == 1) {
                            xu /= 2;
                            p.c.xuTN += xu;
                        }
                        p.c.upxuMessage(xu);
                        p.sendAddchatYellow("B???n nh???n ???????c " + xu + " xu.");
                        break;
                    }
                    case 2: {
                        int luong = Util.nextInt(50,150);
                        if(p.status == 1) {
                            luong /= 2;
                            p.c.luongTN += luong;
                        }
                        p.upluongMessage(luong);
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 1: {
                if (p.c.isNhanban) {
                    p.conn.sendMessageLog( Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if(p.c.level < 40) {
                    p.conn.sendMessageLog("Nh??n v???t ph???i tr??n level 40 m???i c?? th??? nh???n qu?? v?? trang tr??.");
                    return;
                }
                if(p.c.quantityItemyTotal(673) < 1) {
                    p.conn.sendMessageLog("B???n kh??ng c?? ????? Qu?? trang tr?? ????? trang tr?? c??y th??ng Noel.");
                    return;
                }
                if(p.c.getBagNull() < 1) {
                    p.conn.sendMessageLog("H??nh trang kh??ng ????? ch??? tr???ng ????? nh???n qu??");
                    return;
                }
                p.c.pointNoel += 10;
                p.c.removeItemBag(p.c.getIndexBagid(673, false), 1);
                Item it;
                int per = Util.nextInt(300);
                if(per<1) {
                    it = ItemTemplate.itemDefault(383);
                } else if (per >= 1 && per <= 3) {
                    it = ItemTemplate.itemDefault(775);
                } else {
                    per = Util.nextInt(UseItem.idItemCayThong.length);
                    it = ItemTemplate.itemDefault(UseItem.idItemCayThong[per]);
                }
                it.isLock = false;
                it.quantity = 1;
                p.c.addItemBag(true, it);
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
    }

    public static void npcOngGiaNoen(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                if(Server.manager.event != 3) {
                    Service.chatNPC(p, (short) npcid, "Hi???n t???i kh??ng trong th???i gian s??? ki???n Noel");
                    return;
                }
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if(p.c.quantityItemyTotal(775) < 1000) {
                    Service.chatNPC(p, (short) npcid, "B???n kh??ng c?? ????? 1000 hoa tuy???t ????? ?????i m???t n???.");
                    return;
                }
                if(p.c.getBagNull() < 1) {
                    Service.chatNPC(p, (short) npcid, "H??nh trang kh??ng ????? ch??? tr???ng ????? nh???n qu??");
                    return;
                }
                p.c.removeItemBag( p.c.getIndexBagid(775, false), 1000);
                Item it = ItemTemplate.itemDefault(774);
                it.isLock = false;
                it.quantity = 1;
                it.isExpires = true;
                it.expires = System.currentTimeMillis() + 2592000000L;
                p.c.addItemBag(false, it);
                break;
            }
            case 1: {
                if(Server.manager.event != 3) {
                    Service.chatNPC(p, (short) npcid, "Hi???n t???i kh??ng trong th???i gian di???n ra s??? ki???n Noel");
                    return;
                }
                Server.manager.sendTB(p, "H?????ng d???n", "- Ki???m hoa tuy???t b???ng c??ch s??? d???ng B??nh kh??c c??y chocolate, B??nh kh??c c??y d??u t??y ho???c trang tr?? c??y th??ng.\n- D??ng 1000 b??ng hoa tuy???t ????? ?????i l???y m???t n??? Satan v???i ch??? s??? kh???ng.");
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
    }

    public static void npcVuaHung(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                Service.sendInputDialog(p, (short) 9, "Nh???p s??? COIN mu???n ?????i.");
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
           /* case 0: {
                Service.sendInputDialog(p, (short) 9, "Nh???p s??? COIN mu???n ?????i.");
                break;
            }
            case 1: {
                Service.sendInputDialog(p, (short) 1234, "Nh???p s??? l?????ng mu???n ?????i ra coin :");
                break;
            }
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }*/
        }
    }

    public static void npcKanata_LoiDai(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0:
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                if (p.c.party != null && p.c.party.charID != p.c.id) {
                    p.c.party.removePlayer(p.c.id);
                }

                p.c.dunId = -1;
                p.c.isInDun = false;
                p.c.tileMap.leave(p);
                p.restCave();
                p.changeMap(p.c.mapKanata);
                break;
            case 1:
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                if (p.c.party != null && p.c.party.charID != p.c.id) {
                    Service.chatNPC(p, (short)npcid, "Con kh??ng ph???i nh??m tr?????ng, kh??ng th??? ?????t c?????c");
                    return;
                }

                Service.sendInputDialog(p, (short)3, "?????t ti???n c?????c (l???n h??n 1000 xu v?? chia h???t cho 50)");
                break;
            case 2:
                Server.manager.sendTB(p, "H?????ng d???n", "- M???i ?????i th??? v??o l??i ????i\n\n- ?????t ti???n c?????c (L???n h??n 1000 xu v?? chia h???t cho 50)\n\n- Khi c??? 2 ???? ?????t ti???n c?????c, v?? s??? ti???n ph???i th???ng nh???t b???ng nhau th?? tr???n so t??i m???i c?? th??? b???t ?????u.\n\n- Khi ???? ?????t ti???n c?????c, nh??ng tho??t, m???t k???t n???i ho???c thua cu???c, th?? ng?????i ch??i c??n l???i s??? gi??nh chi???n th???ng\n\n- S??? ti???n th???ng s??? nh???n ???????c s??? b??? tr??? ph?? 5%\n\n- N???u h???t th???i gian m?? ch??a c?? ai gi??nh chi???n th???ng th?? cu???c so t??i s??? t??nh ho??, v?? m???i ng?????i s??? nh???n l???i s??? ti???n c???a m??nh v???i m???c ph?? b??? tr??? 1%");
                break;
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }

    }

    public static void npcAdmin(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                if (p.c.isDiemDanh == 0) {
                    if(p.status == 1) {
                        p.upluongMessage(250L);
                        p.c.luongTN += 250;
                    } else {
                        p.upluongMessage(500L);
                    }
                    p.c.isDiemDanh = 1;
                    Service.chatNPC(p, (short) npcid, "??i???m danh th??nh c??ng, con nh???n ???????c 500 l?????ng.");
                } else {
                    Service.chatNPC(p, (short) npcid, "H??m nay con ???? ??i???m danh r???i, h??y quay l???i v??o ng??y h??m sau nha!");
                }
                break;
            }
            case 1: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                if (p.c.isQuaHangDong == 1) {
                    Service.chatNPC(p, (short) npcid, "Con ???? nh???n th?????ng h??m nay r???i!");
                    return;
                }

                if (p.c.countHangDong >= 2) {
                    if(p.status == 1) {
                        p.upluongMessage(750L);
                        p.c.luongTN += 750;
                    } else {
                        p.upluongMessage(1500L);
                    }
                    p.c.isQuaHangDong = 1;
                    Service.chatNPC(p, (short) npcid, "Nh???n qu?? ho??n th??nh hang ?????ng th??nh c??ng, con nh???n ???????c 1500 l?????ng.");
                } else if (p.c.countHangDong < 2) {
                    Service.chatNPC(p, (short) npcid, "Con ch??a ho??n th??nh ????? 2 l???n ??i hang ?????ng, h??y ho??n th??nh ????? 2 l???n v?? quay l???i g???p ta ???? nh???n th?????ng");
                }
                break;
            }
            case 2: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }

                if (p.c.getBagNull() < 6) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                    return;
                }

                if (p.c.level == 1) {
                    p.updateExp(Level.getMaxExp(10));
                    if(p.status == 1) {
                        p.upluongMessage(10000L);
                        p.c.upxuMessage(25000000L);
                        p.c.upyenMessage(25000000L);
                        p.c.luongTN +=  10000;
                        p.c.yenTN += 50000000;
                        p.c.xuTN += 50000000;
                        p.c.addItemBag(false, ItemTemplate.itemDefault(222, true));
                        p.c.addItemBag(false, ItemTemplate.itemDefault(539, true));
                        p.c.addItemBag(false, ItemTemplate.itemDefault(383, false));
                    } else {
                        p.upluongMessage(20000L);
                        p.c.upxuMessage(100000000L);
                        p.c.upyenMessage(100000000L);
                        p.c.addItemBag(false, ItemTemplate.itemDefault(222, true));
                        p.c.addItemBag(false, ItemTemplate.itemDefault(539, true));
                        p.c.addItemBag(false, ItemTemplate.itemDefault(539, true));
                        p.c.addItemBag(false, ItemTemplate.itemDefault(539, true));
                        p.c.addItemBag(false, ItemTemplate.itemDefault(383, false));
                        p.c.addItemBag(false, ItemTemplate.itemDefault(383, false));
                    }
                    Service.chatNPC(p, (short) npcid, "Con ???? nh???n qu?? t??n th??? th??nh c??ng, ch??c con tr???i nghi???m game vui v???.");
                } else {
                    Service.chatNPC(p, (short) npcid, "Con ???? nh???n qu?? t??n th??? tr?????c ???? r???i, kh??ng th??? nh???n l???i l???n n???a!");
                }
                break;
            }
            case 3: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if(p.c.level == 1) {
                    p.conn.sendMessageLog("Kh??ng th??? th???c hi???n thao t??c n??y..");
                    return;
                }
                if(p.c.get().exptype == 1) {
                    p.c.get().exptype = 0;
                    p.sendAddchatYellow("???? t???t nh???n exp.");
                } else {
                    p.c.get().exptype = 1;
                    p.sendAddchatYellow("???? b???t nh???n exp.");
                }
                break;
            }
            case 4: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if(p.status == 1) {
                    Service.chatNPC(p, (short) npcid, "T??i kho???n c???a con ch??a ???????c n??ng c???p l??n CH??NH TH???C, kh??ng th??? nh???n l???i ph???n th?????ng.");
                    return;
                }
                switch (b3) {
                    case 0: {
                        if(p.c.yenTN <= 0) {
                            Service.chatNPC(p, (short) npcid, "Con kh??ng c?? y??n l??u tr??? ????? nh???n l???i.");
                            return;
                        }
                        p.c.upyenMessage(p.c.yenTN);
                        p.c.yenTN = 0;
                        break;
                    }
                    case 1: {
                        if(p.c.xuTN <= 0) {
                            Service.chatNPC(p, (short) npcid, "Con kh??ng c?? xu l??u tr??? ????? nh???n l???i.");
                            return;
                        }
                        p.c.upxuMessage(p.c.xuTN);
                        p.c.xuTN = 0;
                        break;
                    }
                    case 2: {
                        if(p.c.luongTN <= 0) {
                            Service.chatNPC(p, (short) npcid, "Con kh??ng c?? l?????ng l??u tr??? ????? nh???n l???i.");
                            return;
                        }
                        p.upluongMessage(p.c.luongTN);
                        p.c.luongTN = 0;
                        break;
                    }
                    case 3: {
                        if(p.c.expTN <= 0) {
                            Service.chatNPC(p, (short) npcid, "Con kh??ng c?? kinh nghi???m l??u tr??? ????? nh???n l???i.");
                            return;
                        }
                        p.updateExp(p.c.expTN);
                        p.c.expTN = 0;
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
            }
            case 5: {
                if(p.c.isNhanban) {
                    p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if(p.c.clone == null) {
                    Service.chatNPC(p, (short) npcid, "Con kh??ng c?? ph??n th??n ????? s??? d???ng ch???c n??ng n??y.");
                    return;
                }
                Service.startYesNoDlg(p, (byte) 5, "Sau khi l???a ch???n, t???t c??? d??? li???u nh?? trang b???, th?? c?????i, ??i???m,... c???a ph??n th??n s??? b??? reset v??? ban ?????u. H??y ch???c ch???n r???ng b???n ???? th??o to??n b??? trang b??? c???a ph??n th??n v?? x??c nh???n mu???n reset.");
                break;
            }
            case 6: {
                Server.manager.sendTB(p, "H?????ng d???n", "- V???a v??o ch??i, h??y ?????n ch??? ta nh???n qu?? t??n th??? bao g???m: 100tr xu, 20k l?????ng, 100tr y??n \n- M???i ng??y con ???????c ??i???m danh h??ng ng??y 1 l???n v?? nh???n 500 l?????ng \n- N???u m???i ng??y ho??n th??nh hang ?????ng ????? 2 l???n con h??y ?????n ch??? ta v?? Nh???n qu?? hang ?????ng ????? nh???n 1000 l?????ng\n\n** L??u ??, n???u l?? t??i kho???n tr???i nghi???m, con ch??? c?? th??? nh???n ???????c 1 n???a ph???n th?????ng t??? ta.");
                break;
            }
        }
    }

    public static void npcRikudou_ChienTruong(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                p.c.typepk = 0;
                Service.ChangTypePkId(p.c, (byte)0);
                p.c.tileMap.leave(p);
                p.restCave();
                p.changeMap(p.c.mapLTD);
                break;
            }
            case 1: {
                Service.evaluateCT(p.c);
                break;
            }

            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }
    }

    public static void npcKagai_GTC(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch (p.c.mapid) {
            case 117: {
                switch(menuId) {
                    case 0: {
                        p.c.typepk = 0;
                        Service.ChangTypePkId(p.c, (byte)0);
                        p.c.tileMap.leave(p);
                        p.restCave();
                        p.changeMap(p.c.mapLTD);
                        break;
                    }
                    case 1: {
                        Service.chatNPC(p, (short) npcid, "?????t c?????c");
                        Service.sendInputDialog(p, (short)8, "?????t ti???n c?????c (l???n h??n 1000 xu v?? chia h???t cho 50)");
                        break;
                    }

                    default: {
                        Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                        break;
                    }
                }
                break;
            }
            case 118:
            case 119: {
                switch(menuId) {
                    case 0: {
                        p.c.typepk = 0;
                        Service.ChangTypePkId(p.c, (byte)0);
                        p.c.tileMap.leave(p);
                        p.restCave();
                        p.changeMap(p.c.mapLTD);
                        break;
                    }
                    case 1: {
                        Service.evaluateCT(p.c);
                        break;
                    }
                    default: {
                        Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                        break;
                    }
                }
                break;
            }
        }
    }
    
    public static void npcVip(Player p, byte npcid, byte menuId, byte b3) throws IOException{
        short [] nam = {712,713,746,747,748,749,750,751,752};
        short [] nu = {715,716,753,754,755,756,757,758,759};       
        switch(menuId){
            case 0:{
                switch (b3){
                    case 0:{
                        if(p.coinnap >= 20000 && p.vip < 1){                                                                              
                            if (p.c.getBagNull() < 10) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                return;
                            }
                            if(p.c.gender == 1){
                                p.c.addItemBag(false, ItemTemplate.itemDefault(712));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(713));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(746));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(747));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(748));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(749));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(750));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(751));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(752));
                            }else{
                                p.c.addItemBag(false, ItemTemplate.itemDefault(715));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(716));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(753));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(754));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(755));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(756));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(757));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(758));
                                p.c.addItemBag(false, ItemTemplate.itemDefault(759));
                            }
                            p.upluongMessage(100000);
                            p.c.upxuMessage(100000);
                            p.vip = 1;
                            p.conn.sendMessageLog("B???n ???? nh???n vip 1 th??nh c??ng");
                            break;
                        }
                        Service.chatNPC(p, (short) npcid, "B???n kh??ng ????? ??i???u ki???n nh???n VIP");
                        break;
                    }
                    case 1:{
                         if(p.coinnap >= 50000 && p.vip == 1 && p.vip < 2){                                                                                
                            if (p.c.getBagNull() < 10) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                return;
                            }
                            if(p.c.gender == 1){
                                for(byte i = 0; i < 9; i++){                                    
                                    Item itemup = ItemTemplate.itemDefault(nam[i]);
                                    itemup.upgradeNext((byte)8);                                   
                                    p.c.addItemBag(false, itemup);
                                }
                            }else{
                                for(byte i = 0; i < 9; i++){                                    
                                    Item itemup = ItemTemplate.itemDefault(nu[i]);
                                    itemup.upgradeNext((byte)8);                                   
                                    p.c.addItemBag(false, itemup);
                                }                            
                            }
                            p.vip = 2;
                            p.conn.sendMessageLog("B???n ???? nh???n vip 2 th??nh c??ng");
                            break;
                        }
                        Service.chatNPC(p, (short) npcid, "B???n kh??ng ????? ??i???u ki???n nh???n VIP");
                        break;
                    }
                    case 2:{
                        if(p.coinnap >= 100000 && p.vip == 2 && p.vip < 3){                                                                            
                            if (p.c.getBagNull() < 10) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                return;
                            }
                            if(p.c.gender == 1){
                                for(byte i = 0; i < 9; i++){                                    
                                    Item itemup = ItemTemplate.itemDefault(nam[i]);
                                    itemup.upgradeNext((byte)16);                                   
                                    p.c.addItemBag(false, itemup);
                                }
                            }else{
                                for(byte i = 0; i < 9; i++){                                    
                                    Item itemup = ItemTemplate.itemDefault(nu[i]);
                                    itemup.upgradeNext((byte)16);                                   
                                    p.c.addItemBag(false, itemup);
                                }                            
                            }
                            p.vip = 3;
                            p.conn.sendMessageLog("B???n ???? nh???n vip 3 th??nh c??ng");
                            break;
                        }
                        Service.chatNPC(p, (short) npcid, "B???n kh??ng ????? ??i???u ki???n nh???n VIP");
                        break;
                    }
                    case 3:{
                        if(p.coinnap >= 200000 && p.vip == 3 && p.vip < 4){                                                                              
                            if (p.c.getBagNull() < 1) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                return;
                            }
                            p.c.addItemBag(false, ItemTemplate.itemDefault(786));                          
                            p.vip = 4;
                            p.conn.sendMessageLog("B???n ???? nh???n vip 4 th??nh c??ng");
                            break;
                        }
                        Service.chatNPC(p, (short) npcid, "B???n kh??ng ????? ??i???u ki???n nh???n VIP");
                        break;
                    }
                    case 4:{
                        short [] ngokhong = {835, 836};
                        if(p.coinnap >= 500000 && p.vip == 4 && p.vip < 5){                                                                               
                            if (p.c.getBagNull() < 4) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                return;
                            }
                            if (p.c.get().nclass == 0) {
                                p.conn.sendMessageLog("Tr??m c???n nh???p h???c ????? nh???n vip 5");
                                return;
                            }
                            for (byte i = 0; i < 2; i++) {
                                Item itemup = ItemTemplate.itemDefault(ngokhong[i]);
                                itemup.upgradeNext((byte) 16);
                                p.c.addItemBag(false, itemup);
                            }
                            p.c.addItemBag(false, ItemTemplate.itemDefault(837));
                            if (p.c.get().nclass == 1 || p.c.get().nclass == 3 || p.c.get().nclass == 5) {
                                Item itemup = ItemTemplate.itemDefault(833);
                                if (p.c.get().nclass == 1) {
                                    itemup.sys = 1;
                                } else if (p.c.get().nclass == 3) {
                                    itemup.sys = 2;
                                } else if (p.c.get().nclass == 5) {
                                    itemup.sys = 3;
                                }
                                itemup.upgradeNext((byte) 16);
                                p.c.addItemBag(false, itemup);
                            }
                            if (p.c.get().nclass == 2 || p.c.get().nclass == 4 || p.c.get().nclass == 6) {
                                Item itemup = ItemTemplate.itemDefault(834);
                                if (p.c.get().nclass == 2) {
                                    itemup.sys = 1;
                                } else if (p.c.get().nclass == 4) {
                                    itemup.sys = 2;
                                } else if (p.c.get().nclass == 6) {
                                    itemup.sys = 3;
                                }
                                itemup.upgradeNext((byte) 16);
                                p.c.addItemBag(false, itemup);
                            }
                            p.vip = 5;
                            p.conn.sendMessageLog("Tr??m ???? nh???n vip 5 th??nh c??ng");
                            break;
                        }
                        Service.chatNPC(p, (short) npcid, "B???n kh??ng ????? ??i???u ki???n nh???n VIP");
                        break;
                    }
                    case 5:{
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if(p.coinnap >= 1000000 && p.vip == 5 && p.vip < 6){
                            if (p.c.getBagNull() < 1) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                return;
                            }
                            Item itemup = ItemTemplate.itemDefault(828);
                            p.c.addItemBag(false, itemup);
                            p.vip = 6;
                            p.conn.sendMessageLog("Tr??m ???? nh???n vip 6 th??nh c??ng");
                            break;                
                        }
                        Service.chatNPC(p, (short) npcid, "B???n kh??ng ????? ??i???u ki???n nh???n VIP");
                        break;
                    }
                    
                }
                break;
            }
            case 1:{
                switch(p.vip){
                    case 1:{
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isDiemDanh == 0) {
                            if (p.c.getBagNull() < 1) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                return;
                            }
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            Item itemup = ItemTemplate.itemDefault(789);
                            itemup.quantity = 10;                                   
                            p.c.addItemBag(false, itemup);
                            p.upluongMessage(500L);
                            p.c.isDiemDanh = 1;
                            Service.chatNPC(p, (short) npcid, "B??o danh VIP 1 th??nh c??ng, con nh???n ???????c 500 l?????ng.");
                            break;
                        } else {
                            Service.chatNPC(p, (short) npcid, "H??m nay tr??m ???? B??o danh VIP, h??y quay l???i v??o ng??y h??m sau nha!");
                        }
                        break;
                    }
                    case 2:{
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isDiemDanh == 0) {
                            if (p.c.getBagNull() < 2) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                return;
                            }
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            Item itemup = ItemTemplate.itemDefault(789);
                            itemup.quantity = 20;                                   
                            p.c.addItemBag(false, itemup);
                            p.upluongMessage(1000L);
                            p.c.isDiemDanh = 1;
                            Service.chatNPC(p, (short) npcid, "Tr??m b??o danh VIP 2 th??nh c??ng, tr??m nh???n ???????c 1000 l?????ng.");
                            break;
                        } else {
                            Service.chatNPC(p, (short) npcid, "H??m nay tr??m ???? B??o danh VIP, h??y quay l???i v??o ng??y h??m sau nha!");
                        }
                        break;
                    }
                    case 3:{
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isDiemDanh == 0) {
                            if (p.c.getBagNull() < 3) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                return;
                            }
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            Item itemup = ItemTemplate.itemDefault(789);
                            itemup.quantity = 30;                                   
                            p.c.addItemBag(false, itemup);
                            p.upluongMessage(5000L);
                            p.c.isDiemDanh = 1;
                            Service.chatNPC(p, (short) npcid, "Tr??m b??o danh VIP 3 th??nh c??ng, tr??m nh???n ???????c 5000 l?????ng.");
                            break;
                        } else {
                            Service.chatNPC(p, (short) npcid, "H??m nay tr??m ???? B??o danh VIP, h??y quay l???i v??o ng??y h??m sau nha!");
                        }
                        break;
                    }
                    case 4:{
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isDiemDanh == 0) {
                            if (p.c.getBagNull() < 2) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                return;
                            }
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            Item itemup = ItemTemplate.itemDefault(789);
                            itemup.quantity = 40;                          
                            p.c.addItemBag(false, itemup);
                            p.upluongMessage(10000L);
                            p.c.isDiemDanh = 1;
                            Service.chatNPC(p, (short) npcid, "Tr??m b??o danh VIP 4 th??nh c??ng, tr??m nh???n ???????c 5000 l?????ng.");
                            break;
                        } else {
                            Service.chatNPC(p, (short) npcid, "H??m nay tr??m ???? B??o danh VIP, h??y quay l???i v??o ng??y h??m sau nha!");
                        }
                        break;
                    }
                    case 5:{
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isDiemDanh == 0) {
                            if (p.c.getBagNull() < 6) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                return;
                            }
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            Item itemup = ItemTemplate.itemDefault(789);
                            itemup.quantity = 50;                          
                            p.c.addItemBag(false, itemup);
                            p.upluongMessage(20000L);
                            p.c.isDiemDanh = 1;
                            Service.chatNPC(p, (short) npcid, "Tr??m b??o danh VIP 5 th??nh c??ng, tr??m nh???n ???????c 5000 l?????ng.");
                            break;
                        } else {
                            Service.chatNPC(p, (short) npcid, "H??m nay tr??m ???? B??o danh VIP, h??y quay l???i v??o ng??y h??m sau nha!");
                        }
                        break;
                    }
                    case 6:{
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }

                        if (p.c.isDiemDanh == 0) {
                            if (p.c.getBagNull() < 6) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_ENOUGH_BAG);
                                return;
                            }
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            p.c.addItemBag(false, ItemTemplate.itemDefault(540));
                            Item itemup = ItemTemplate.itemDefault(789);
                            itemup.quantity = 100;                          
                            p.c.addItemBag(false, itemup);
                            p.upluongMessage(40000L);
                            p.c.isDiemDanh = 1;
                            Service.chatNPC(p, (short) npcid, "Tr??m b??o danh VIP 6 th??nh c??ng, tr??m nh???n ???????c 5000 l?????ng.");
                            break;
                        } else {
                            Service.chatNPC(p, (short) npcid, "H??m nay tr??m ???? B??o danh VIP, h??y quay l???i v??o ng??y h??m sau nha!");
                        }
                        break;
                    }
                }
                break;
            }
            case 2:{
                switch (p.vip){
                    case 1:{
                        if(p.c.yen >= 500000 && p.c.isQuaHangDong == 0){
                            p.c.isQuaHangDong = 1;
                            p.c.upyenMessage(-500000);
                            p.c.upxuMessage(500000);
                            Service.chatNPC(p, (short) npcid, "?????i xu th??nh c??ng!");
                            break;
                        }else if(p.c.isQuaHangDong != 0){
                            Service.chatNPC(p, (short) npcid, "Tr??m ???? ?????i xu r???i, xin quay l???i v??o ng??y mai!");
                            break;
                        }else{
                            Service.chatNPC(p, (short) npcid, "Kh??ng ????? y??n!");
                        }
                        break;
                    }
                    case 2:{
                        if(p.c.yen >= 1000000 && p.c.isQuaHangDong == 0){
                            p.c.isQuaHangDong = 1;
                            p.c.upyenMessage(-1000000);
                            p.c.upxuMessage(1000000);
                            Service.chatNPC(p, (short) npcid, "?????i xu th??nh c??ng!");
                            break;
                        }else if(p.c.isQuaHangDong != 0){
                            Service.chatNPC(p, (short) npcid, "Tr??m ???? ?????i xu r???i, xin quay l???i v??o ng??y mai!");
                            break;
                        }else{
                            Service.chatNPC(p, (short) npcid, "Kh??ng ????? y??n!");
                        }
                        break;
                    }
                    case 3:{
                        if(p.c.yen >= 2000000 && p.c.isQuaHangDong == 0){
                            p.c.isQuaHangDong = 1;
                            p.c.upyenMessage(-2000000);
                            p.c.upxuMessage(2000000);
                            Service.chatNPC(p, (short) npcid, "?????i xu th??nh c??ng!");
                            break;
                        }else if(p.c.isQuaHangDong != 0){
                            Service.chatNPC(p, (short) npcid, "Tr??m ???? ?????i xu r???i, xin quay l???i v??o ng??y mai!");
                            break;
                        }else{
                            Service.chatNPC(p, (short) npcid, "Kh??ng ????? y??n!");
                        }
                        break;
                    }
                    
                    case 4:{
                        if(p.c.yen >= 2000000 && p.c.isQuaHangDong == 0){
                            p.c.isQuaHangDong = 1;
                            p.c.upyenMessage(-5000000);
                            p.c.upxuMessage(5000000);
                            Service.chatNPC(p, (short) npcid, "?????i xu th??nh c??ng!");
                            break;
                        }else if(p.c.isQuaHangDong != 0){
                            Service.chatNPC(p, (short) npcid, "Tr??m ???? ?????i xu r???i, xin quay l???i v??o ng??y mai!");
                            break;
                        }else{
                            Service.chatNPC(p, (short) npcid, "Kh??ng ????? y??n!");
                        }
                        break;
                    }
                    
                    case 5:{
                        if(p.c.yen >= 15000000 && p.c.isQuaHangDong == 0){
                            p.c.isQuaHangDong = 1;
                            p.c.upyenMessage(-15000000);
                            p.c.upxuMessage(15000000);
                            Service.chatNPC(p, (short) npcid, "?????i xu th??nh c??ng!");
                            break;
                        }else if(p.c.isQuaHangDong != 0){
                            Service.chatNPC(p, (short) npcid, "Tr??m ???? ?????i xu r???i, xin quay l???i v??o ng??y mai!");
                            break;
                        }else{
                            Service.chatNPC(p, (short) npcid, "Kh??ng ????? y??n!");
                        }
                        break;
                    }
                    
                    case 6:{
                        if(p.c.yen >= 30000000 && p.c.isQuaHangDong == 0){
                            p.c.isQuaHangDong = 1;
                            p.c.upyenMessage(-30000000);
                            p.c.upxuMessage(30000000);
                            Service.chatNPC(p, (short) npcid, "?????i xu th??nh c??ng!");
                            break;
                        }else if(p.c.isQuaHangDong != 0){
                            Service.chatNPC(p, (short) npcid, "Tr??m ???? ?????i xu r???i, xin quay l???i v??o ng??y mai!");
                            break;
                        }else{
                            Service.chatNPC(p, (short) npcid, "Kh??ng ????? y??n!");
                        }
                        break;
                    }
                    
                    
                }
                break;
            }
            case 3:{
                switch(b3){
                    case 0:{
                        if(p.vip < 1){
                            Service.chatNPC(p, (short) npcid, "Vip 1 m???i ???????c s??? d???ng ch???c n??ng b???t t???t exp");
                            return;
                        }else{
                            if (p.c.isNhanban) {
                                Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                                return;
                            }
                            if(p.c.level == 1) {
                                p.conn.sendMessageLog("Kh??ng th??? th???c hi???n thao t??c n??y..");
                                return;
                            }
                            if(p.c.get().exptype == 1) {
                                p.c.get().exptype = 0;
                                p.sendAddchatYellow("???? t???t nh???n exp.");
                            } else {
                                p.c.get().exptype = 1;
                                p.sendAddchatYellow("???? b???t nh???n exp.");
                            }
                        }
                        break;
                    }
                    case 1:{
                        if(p.vip < 2){
                            Service.chatNPC(p, (short) npcid, "Vip 2 m???i ???????c s??? d???ng ch???c n??ng b???t t???t exp");
                            return;
                        }else{
                            if(p.c.maxluggage >= 120){
                                Service.chatNPC(p, (short) npcid, "Tr??m ???? l??n 120 ?? h??nh trang r???i");
                                break;
                                }else if(p.c.levelBag < 3){
                                p.conn.sendMessageLog("B???n c???n s??? d???ng t??i v???i c???p 3 ????? m??? th??m h??nh trang");
                            }else{
                                p.c.levelBag = 4;
                                p.c.maxluggage = 120;
                                p.conn.sendMessageLog("N??ng th??nh c??ng, b???n c???n ph???i tho??t game ????? l??u");
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case 4: {
                Server.manager.sendTB(p, "M???c Vip", "- N???p ????? m???c s??? nh???n th?????ng.\n"
                        + "- Vip 1: 20k\n"
                        + "Nh???n 1 set Jirai ho???c Yumito + 0\n"
                        + "- Vip 2: 50k\n"
                        + "Nh???n 1 set Jirai ho???c Yumito + 8\n"
                        + "- Vip 3: 100k\n"
                        + "Nh???n 1 set Jirai ho???c Yumito + 16\n"
                        + "- Vip 4: 200k\n"
                        + "Nh???n m???t n??? Sumimura + 0 c?? th??? n??ng c???p\n"
                        + "- Vip 5: 500k\n"
                        + "Nh???n 1 set T??n Ng??? Kh??ng + 16 m???t n??? c?? th??? n??ng c???p\n"
                        + "- Vip 6: 1tr\n"
                        + "Nh???n 1 Ph?????ng Ho??ng B??ng si??u VIP\n");
                break;
            }
        }
    }
 
    public static void npcMiNuong(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId) {
            case 0: {
                switch(p.vxLuong){
                    case 0: {
                        if(p.vip < 1){
                            p.conn.sendMessageLog("B???n c???n l?? vip ????? tham gia");
                            break;
                        }else {
                            if(p.luong < 77000){
                                p.conn.sendMessageLog("L?????t 1 c???n 77k l?????ng");
                                break;
                            }
                            Service.startYesNoDlg(p, (byte) 6, "Tr??m s??? m???t 77k l?????ng ????? nh???n l???i ng???u nhi??n 107k ?????n 177k l?????ng?");
                        }
                        break;
                    }
                    case 1: {
                        if(p.vip < 2){
                            p.conn.sendMessageLog("B???n c???n t???i thi???u vip 2 ????? tham gia");
                            break;
                        }else {
                            if(p.luong < 277000){
                                p.conn.sendMessageLog("L?????t 2 c???n 277k l?????ng");
                                break;
                            }
                            Service.startYesNoDlg(p, (byte) 7, "Tr??m s??? m???t 277k l?????ng ????? nh???n l???i ng???u nhi??n 377k ?????n 577k l?????ng?");
                        }
                        break;
                    }
                    case 2: {
                        if(p.vip < 3){
                            p.conn.sendMessageLog("B???n c???n t???i thi???u vip 3 ????? tham gia");
                            break;
                        }else {
                            if(p.luong < 777000){
                                p.conn.sendMessageLog("L?????t 3 c???n 777k l?????ng");
                                break;
                            }
                            Service.startYesNoDlg(p, (byte) 9, "Tr??m s??? m???t 777k l?????ng ????? nh???n l???i ng???u nhi??n 1000k ?????n 1777k l?????ng?");
                        }
                        break;
                    }
                    case 3: {
                        if(p.vip < 4){
                            p.conn.sendMessageLog("B???n c???n t???i thi???u vip 4 ????? tham gia");
                            break;
                        }else {
                            if(p.luong < 2077000){
                                p.conn.sendMessageLog("L?????t 4 c???n 2077k l?????ng");
                                break;
                            }
                            Service.startYesNoDlg(p, (byte) 10, "Tr??m s??? m???t 2077k l?????ng ????? nh???n l???i ng???u nhi??n 2777k ?????n 4377k l?????ng?");
                        }
                        break;
                    }
                    case 4: {
                        if(p.vip < 5){
                            p.conn.sendMessageLog("B???n c???n t???i thi???u vip 5 ????? tham gia");
                            break;
                        }else {
                            if(p.luong < 5077000){
                                p.conn.sendMessageLog("L?????t 5 c???n 5077k l?????ng");
                                break;
                            }
                            Service.startYesNoDlg(p, (byte) 11, "Tr??m s??? m???t 5077k l?????ng ????? nh???n l???i ng???u nhi??n 6777k ?????n 7777k l?????ng?");
                        }
                        break;
                    }
                    case 5: {
                        if(p.vip < 6){
                            p.conn.sendMessageLog("B???n c???n t???i thi???u vip 6 ????? tham gia");
                            break;
                        }else {
                            if(p.luong < 10777000){
                                p.conn.sendMessageLog("L?????t 5 c???n 10777k l?????ng");
                                break;
                            }
                            Service.startYesNoDlg(p, (byte) 12, "Tr??m s??? m???t 10777k l?????ng ????? nh???n l???i ng???u nhi??n 17777k ?????n 27777k l?????ng?");
                        }
                        break;
                    }                  
                    default: {
                        p.conn.sendMessageLog("B???n ???? quay full m???c");
                        break;
                    }
                }
                break;
            }
            case 1: {
                short [] phale = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
                int [] moneyUpMat = {10000000, 50000000, 70000000, 100000000, 150000000, 200000000, 300000000, 350000000, 400000000, 500000000};
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                Item itemup = p.c.ItemBody[11];
                if((p.c.ItemBody[11] == null)){
                    p.conn.sendMessageLog("B???n c???n ??eo m???t n??? Ng??? Kh??ng ho???c sumimura");
                    return;
                }
                System.out.println(p.c.ItemBody[11].id);
                if(p.c.ItemBody[11].id != 786 && p.c.ItemBody[11].id != 837){
                    p.conn.sendMessageLog("B???n c???n ??eo m???t n??? Ng??? Kh??ng ho???c sumimura");
                    return;
                }
                if(itemup.upgrade > 9){
                    p.conn.sendMessageLog("M???t n??? ?????t c???p t???i ??a");
                    return;
                }
                if(p.c.quantityItemyTotal(789) < phale[itemup.upgrade]){
                    p.conn.sendMessageLog("B???n kh??ng ????? " + phale[itemup.upgrade] + " pha l?? ????? n??ng m???t n???!");
                    return;
                }
                if(p.c.quantityItemyTotal(789) < phale[itemup.upgrade]){
                    p.conn.sendMessageLog("B???n kh??ng ????? " + phale[itemup.upgrade] + " pha l?? ????? n??ng m???t n???!");
                    return;
                }
                if((p.c.xu) < moneyUpMat[p.c.ItemBody[11].upgrade]) {
                    p.conn.sendMessageLog("B???n kh??ng ????? " + moneyUpMat[itemup.upgrade] + " xu ????? n??ng c???p m???t n???");
                    return;
                }
                if (p.c.getBagNull() < 1) {
                    Service.chatNPC(p, (short) npcid, "H??nh trang c???a tr??m kh??ng ????? ch??? tr???ng ????? nh???n m???t n???!");
                    return;
                }
                p.c.upxuMessage(-(moneyUpMat[itemup.upgrade]));
                p.c.removeItemBags(789, phale[itemup.upgrade]);
                p.c.removeItemBody((byte)11);
                itemup.quantity = 1;
                itemup.ncMatNa((byte)1);               
                p.c.addItemBag(false, itemup);
                break;
            }
            case 2:{
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                if(p.c.quantityItemyTotal(838) < 1){
                    p.conn.sendMessageLog("B???n kh??ng ????? c?? V?? M??n L???nh");
                    return;
                }
                Skill skill2 = new Skill();
                p.c.skill.add(skill2);
                p.c.nclass = 0;
                p.c.KSkill = new byte[3];
                p.c.OSkill = new byte[5];
                byte i;
                for(i = 0; i < p.c.KSkill.length; ++i) {
                    p.c.clone.KSkill[i] = -1;
                }
                for(i = 0; i < p.c.OSkill.length; ++i) {
                    p.c.OSkill[i] = -1;
                }
                p.c.CSkill = -1;
                p.c.removeItemBags(838, 1);
                p.conn.sendMessageLog("B???n chuy???n v??? v?? m??n ph??i th??nh c??ng");
                break;
            }
            case 3:{
                break;
            }
            case 4:{
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                Item itemup = p.c.ItemBody[1];
                if((itemup == null)){
                    p.conn.sendMessageLog("B???n ch??a mang v?? kh??");
                    return;
                }
                if (p.c.getBagNull() < 1) {
                    Service.chatNPC(p, (short) npcid, "H??nh trang kh??ng ????? ch??? tr???ng");
                    return;
                }
                if(itemup.VKTop() == 300){
                    p.c.removeItemBody((byte)1);
                    Item tang = ItemTemplate.itemDefault(839);
                    tang.quantity = 300;
                    p.c.addItemBag(false, tang);
                    break;
                }
                else if(itemup.VKTop() == 500){
                    p.c.removeItemBody((byte)1);
                    Item tang = ItemTemplate.itemDefault(839);
                    tang.quantity = 500;
                    p.c.addItemBag(false, tang);
                    break;
                }
                else {
                   p.conn.sendMessageLog("B???n kh??ng ??eo ????? top");
                }
                break;
            }
            case 5: {
                if (p.c.level < 150) {
                    Service.chatNPC(p, (short) npcid, "anh em ???? ?????t c???p 150 ????u???\nKhi n??o ????? t??? tin h??y quay l???i g???p ta nh??.! \nM??y h??y c??? g???ng nhi???u l??n anh em..!");
                    return;
                }
                if (p.c.expCS < 2000000000){
                    p.conn.sendMessageLog("Anh em kh??ng ????? 2 t??? exp chuy???n sinh");
                    return;
                }
                if(p.c.quantityItemyTotal(843) < 4000){
                    p.conn.sendMessageLog("Anh em kh??ng ????? 4000 b??p c???i l??o");
                    return;
                }
                if(p.c.quantityItemyTotal(842) < 10){
                    p.conn.sendMessageLog("Anh em kh??ng ????? 10 chuy???n sinh ??an");
                    return;
                }
                if (p.c.chuyenSinh == 1) {
                    Service.chatNPC(p, (short) npcid, "server ch??? m???i cho chuy???n sinh 1, s??? c???p nh???t sau");
                    return;
                }
                if (p.luong < 5000000) {
                    Service.chatNPC(p, (short) npcid, "H??nh trang con ko c?? ????? h???c ph?? 5tr l?????ng ????? ta mua cafe s??ng.\nH??y ??i s??n boss v?? ki???m ????? l?????ng ????? chuy???n sinh nh?? anh em y??u qu?? c???a ta ??i..!");
                    return;
                }
                if (p.c.xu < 50000000) {
                    Service.chatNPC(p, (short) npcid, "H??nh trang con ko c?? ????? h???c ph?? 100tr xu ????? ta mua cafe s??ng.\nH??y ??i s??n boss v?? ki???m ????? l?????ng ????? chuy???n sinh nh?? anh em y??u qu?? c???a ta ??i..!");
                    return;
                }
                p.c.removeItemBags(843, 4000);
                p.c.removeItemBags(842, 10);
                p.c.expCS -= 2000000000;
                p.c.chuyenSinh++;
                p.updateExp(Level.getMaxExp(10) - p.c.exp);
                p.upluongMessage(-5000000);
                p.c.upxuMessage(-50000000);
                Manager.chatKTG("Ch??c m???ng anh: " + p.c.name + " ???? ?????t c???nh gi???i chuy???n sinh 1. Ch??ng ta h??y c??ng " + p.c.name + " quay l???i tu???i th?? d??? d???i v?? vi???t l??n 1 h??nh tr??nh m???i ?????y v??? vang n??o. Anh em nh??n " + p.c.name + " m?? h???c h???i nh??.!");
                break;
            }
            case 6:{
                if(p.vip < 1){
                    p.conn.sendMessageLog("B???n c???n t???i thi???u vip 1 ????? tham gia");
                    break;
                }
                    Service.startYesNoDlg(p, (byte) 13, "Tr??m s??? xo?? s???ch r????ng ????? c???a ch??nh m??nh?");
                    break;
            }
                /*{
                if(p.luong < 10000){
                    p.conn.sendMessageLog("B???n kh??ng ????? l?????ng ????? tham gia");
                    return;
                }
                byte per = (byte) Util.nextInt(0, 100);
                short[] arId;
                short aID = 0;
                if(per <= 50){
                    arId = new short[]{840, 838};
                    aID = arId[Util.nextInt(arId.length)];
                    Item itemup = ItemTemplate.itemDefault(aID);
                    p.c.addItemBag(false, itemup);
                }else{
                    p.conn.sendMessageLog("??en l???m con ???");
                }
                p.c.luongTop += 10000;
                p.upluongMessage(-10000);
                break;
            }*/
            default: {
                Service.chatNPC(p, (short) npcid, "Ch???c n??ng n??y ??ang c???p nh???t!");
                break;
            }
        }       
    }
   
    public static void npcSuKien(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId){
            case 0:{
                switch(b3){
                    case 0:{
                        if(p.luong < 10000){
                            p.conn.sendMessageLog("B???n kh??ng ????? 10k l?????ng ????? ti??u l?????ng");
                        }
                        long luong = 10000;
                        p.c.luongTop += 10000;
                        p.upluongMessage(-luong);
                        break;
                    }
                    case 2:{
                        // n???u mu???n ti??u l?????ng n?? c???ng th?? ++ p.c.luongTN += gi?? tr??? ;
                        //trong n??y nh??
                         Server.manager.sendTB(p, "Top ti??u l?????ng", Rank.getStringBXH(5));
                        break;
                    }
                }
                break;
            }
        }
    }
    
    public static void npcPhoBan(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId){
            case 0: {             
                switch (b3){
                    case 0: {
                        if (p.c.isNhanban) {
                            Service.chatNPC(p, (short)npcid, Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if(TuTien.tuTien == null) {
                            Service.chatNPC(p, (short)npcid, "B??y gi??? ch??a ph???i th???i gian ????? tu luy???n.");
                            return;
                        }
                        if(p.c.level < 140) {
                            Service.chatNPC(p, (short)npcid, "T???i thi???u 140 c???p.");
                            return;
                        }
                        if (p.vip < 3) {
                            Service.chatNPC(p, (short)npcid, "Ph???i tr??n vip 3 m???i c?? th??? v??o.");
                            return;
                        }
                        Map ma = Manager.getMapid(TuTien.tuTien.map[0].id);
                        for (TileMap area : ma.area) {
                            if (area.numplayers < ma.template.maxplayers) {
                                p.c.tileMap.leave(p);
                                area.EnterMap0(p.c);
                                return;
                            }
                        }
                        return;
                    }
                    case 1: {
                        break;
                    }
                       case 2: {
                        if (p.c.isNhanban) {
                            p.conn.sendMessageLog(Language.NOT_FOR_PHAN_THAN);
                            return;
                        }
                        if (p.c.pk > 0) {
                            p.sendAddchatYellow("Kh??ng th??? v??o l??ng Las khi c?? ??i???m hi???u chi???n l???n h??n 0");
                            return;
                        }
                        p.c.tileMap.leave(p);
                        Map map = Server.maps[165];
                        byte k;
                        for (k = 0; k < map.area.length; k++) {
                            if (map.area[k].numplayers < map.template.maxplayers) {
                                map.area[k].EnterMap0(p.c);
                                break;
                            }
                        }
                        p.endLoad(true);
                        break;
                    }
                }
            }
        }
    }
    
    public static void npcNangCap(Player p, byte npcid, byte menuId, byte b3) throws IOException {
        switch(menuId){
            case 0: {
                if (p.c.isNhanban) {
                    Service.chatNPC(p, (short) npcid, Language.NOT_FOR_PHAN_THAN);
                    return;
                }
                Item itemup = p.c.ItemBody[12];
                if((itemup == null)){
                    p.conn.sendMessageLog("B???n ch??a mang Yoroi");
                    return;
                }
                if(itemup.upgrade == 16) {
                    p.conn.sendMessageLog("Yoroi max c???p ?????");
                    return;
                }
                int[] xu = new int[16];
                xu[0] = 10000000;
                for(byte i = 1; i < 16; i++) {
                    xu[i] = xu[i - 1] + 10000000;
                }              
                if(p.c.xu < xu[itemup.upgrade]){
                    p.conn.sendMessageLog("B???n kh??ng ????? " + xu[itemup.upgrade] + " xu ????? n??ng c???p yoroi");
                    return;
                }
                if (p.c.quantityItemyTotal(222) < 1 || p.c.quantityItemyTotal(223) < 1 || p.c.quantityItemyTotal(224) < 1 || p.c.quantityItemyTotal(225) < 1 || p.c.quantityItemyTotal(226) < 1 || p.c.quantityItemyTotal(227) < 1 || p.c.quantityItemyTotal(228) < 1) {
                    p.conn.sendMessageLog("B???n kh??ng c?? ????? 7 vi??n ng???c r???ng 1 - 7 sao ????? n??ng c???p Yoroi.");
                    return;
                }
                p.c.upxuMessage(-(xu[itemup.upgrade]));
                p.c.removeItemBody((byte)12);
                for (int i = 222; i <= 228; i++) {
                    if (p.c.getIndexBagid(i, false) != -1) {
                        p.c.removeItemBag(p.c.getIndexBagid(i, false), 1);
                    } else {
                        p.c.removeItemBag(p.c.getIndexBagid(i, true), 1);
                    }
                }
                itemup.ncYoroi((byte)1);
                p.c.addItemBag(false, itemup);
                break;
            }
        }
    }
    
}
