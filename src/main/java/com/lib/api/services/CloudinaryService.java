package com.lib.api.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lib.api.entities.Imagen;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@Service
public class CloudinaryService {
//
//    Cloudinary cloudinary;
//    private Map<String, String> valuesMap = new HashMap<>();
//    public CloudinaryService(){
//        valuesMap.put("cloud_name", "drt1ea5my");
//        valuesMap.put("api_key", "318594348978517");
//        valuesMap.put("api_secret", "MX2eqiEhxtJYpJLSW5ZAZVGVUAk");
//        cloudinary = new Cloudinary(valuesMap);
//    }
//    public Map upload(MultipartFile multipartFile) throws IOException{
//        File file = convert(multipartFile);
//        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
//        file.delete();
//
//        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
//        if (bi == null){
//            return ;
//        }
//        Map result = cloudinaryService.upload(multipartFile);
//        Imagen imagen = new Imagen((Long)result.get("id"),(String)result.get("original_filename"),
//                (String)result.get("url"),
//                (String)result.get("public_id"));
//        imagenServiceImpl.save(imagen);
//
//
//
//        return result;
//    }
//
//    public Map delete (String id) throws IOException{
//        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
//        return result;
//    }
//
//    private File convert(MultipartFile multipartFile) throws IOException {
//        File file = new File(multipartFile.getOriginalFilename());
//        FileOutputStream fo = new FileOutputStream(file);
//        fo.write(multipartFile.getBytes());
//        fo.close();
//        return file;
//    }
}
