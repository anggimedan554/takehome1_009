/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pws.c.WarungSayur;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Lenovo
 */
@Controller
public class controllerwarung {
    @RequestMapping("/ANGGY")
    public String prosesInputan (HttpServletRequest data, Model get)
    {
        String inputBuah = data.getParameter("namasayur");
        String inputHarga = data.getParameter("hargaperkilo");
        String inputBayar = data.getParameter("uangdibayar");
        Integer uangBayar = Integer.valueOf(inputBayar);
        Integer hargaBuah = Integer.valueOf(inputHarga);
        String inputJumlahBeli = data.getParameter("jumlahdibeli");
        Double jumlahBeli = Double.valueOf(inputJumlahBeli);
        Double jumlahBayar = hargaBuah * jumlahBeli;
        Integer disk = null;
        Double hargadiskon;
        String keterangan = " ";
        
        if (jumlahBayar <= 16000)
        {
            disk = 0;
        }
        else if (jumlahBayar > 16000 && jumlahBayar < 25000)
        {
           disk = 10;
        }
        else if (jumlahBayar > 25000)
        {
            disk = 15;
        }
       
        hargadiskon = jumlahBayar*disk/100;
        Double totalBayar = jumlahBayar - hargadiskon;
        
        Double kembali =  uangBayar - totalBayar;
        Double kurang = totalBayar - uangBayar;
        
        if (totalBayar < uangBayar)
        {
            keterangan = "Kembalian anda Rp. " + kembali;
        }
        
        else if (totalBayar > uangBayar)
        {
            keterangan = "Uang anda kurang Rp. " + kurang;
        }
        
        get.addAttribute("varbuah", inputBuah);
        get.addAttribute("varharga", inputHarga);
        get.addAttribute("varjumlah", inputJumlahBeli);
        get.addAttribute("total", jumlahBayar);
        get.addAttribute("vartotal", totalBayar);
        get.addAttribute("discount", hargadiskon);
        get.addAttribute("disc", disk);
        get.addAttribute("bayar", inputBayar);
        get.addAttribute("ket", keterangan);
        
        return "warungsayur";
    }

}