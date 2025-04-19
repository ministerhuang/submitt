package com.yaya.submitt.service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类似Python风格的EXIF信息提取工具类
 */
public class ExifUtils {

    /**
     * 从MultipartFile获取EXIF数据并返回Map格式
     *
     * @param file 上传的图片文件
     * @return 包含EXIF信息的Map
     */
    public static Map<String, String> getExifData(MultipartFile file) {
        Map<String, String> exifData = new HashMap<>();

        if (file == null || file.isEmpty()) {
            return exifData;
        }

        try {
            // 使用ImageMetadataReader从输入流读取元数据
            Metadata metadata = ImageMetadataReader.readMetadata(file.getInputStream());

            // 提取拍摄时间
            ExifSubIFDDirectory exifSubDir = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (exifSubDir != null) {
                // 尝试获取原始拍摄时间
                Date dateTime = exifSubDir.getDateOriginal();
                if (dateTime != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                    exifData.put("DateTime", formatter.format(dateTime));
                } else if (exifSubDir.containsTag(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL)) {
                    // 如果Date对象获取失败，尝试直接获取字符串
                    exifData.put("DateTime", exifSubDir.getString(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL));
                }
            }

            // 如果无法从ExifSubIFD获取时间，尝试从ExifIFD0获取
            if (!exifData.containsKey("DateTime")) {
                ExifIFD0Directory exifIFD0 = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
                if (exifIFD0 != null && exifIFD0.containsTag(ExifIFD0Directory.TAG_DATETIME)) {
                    exifData.put("DateTime", exifIFD0.getString(ExifIFD0Directory.TAG_DATETIME));
                }
            }

            // 提取相机制造商和型号信息
            ExifIFD0Directory exifIFD0 = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            if (exifIFD0 != null) {
                if (exifIFD0.containsTag(ExifIFD0Directory.TAG_MAKE)) {
                    exifData.put("Make", exifIFD0.getString(ExifIFD0Directory.TAG_MAKE));
                }

                if (exifIFD0.containsTag(ExifIFD0Directory.TAG_MODEL)) {
                    exifData.put("Model", exifIFD0.getString(ExifIFD0Directory.TAG_MODEL));
                }

                // 如果同时有Make和Model，组合成一个设备信息
                if (exifData.containsKey("Make") && exifData.containsKey("Model")) {
                    String make = exifData.get("Make").trim();
                    String model = exifData.get("Model").trim();
                    exifData.put("Device", make + " " + model);
                } else if (exifData.containsKey("Model")) {
                    exifData.put("Device", exifData.get("Model").trim());
                } else if (exifData.containsKey("Make")) {
                    exifData.put("Device", exifData.get("Make").trim());
                }
            }

            // 提取GPS位置信息
            GpsDirectory gpsDir = metadata.getFirstDirectoryOfType(GpsDirectory.class);
            if (gpsDir != null) {
                try {
                    GeoLocation location = gpsDir.getGeoLocation();
                    if (location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        exifData.put("Location", String.format("%.6f, %.6f", latitude, longitude));
                        exifData.put("Latitude", String.format("%.6f", latitude));
                        exifData.put("Longitude", String.format("%.6f", longitude));
                    }
                } catch (Exception e) {
                    // 如果无法获取GeoLocation，尝试分别获取经纬度
                    if (gpsDir.containsTag(GpsDirectory.TAG_LATITUDE) &&
                            gpsDir.containsTag(GpsDirectory.TAG_LONGITUDE)) {
                        exifData.put("Location",
                                gpsDir.getString(GpsDirectory.TAG_LATITUDE) +
                                        gpsDir.getString(GpsDirectory.TAG_LATITUDE_REF) + ", " +
                                        gpsDir.getString(GpsDirectory.TAG_LONGITUDE) +
                                        gpsDir.getString(GpsDirectory.TAG_LONGITUDE_REF));
                    }
                }
            }

        } catch (ImageProcessingException | IOException e) {
            // 处理异常但不中断程序
            // 可以在这里添加日志记录
        }

        return exifData;
    }

    /**
     * 直接获取拍摄设备信息
     */
    public static String getDevice(MultipartFile file) {
        Map<String, String> exifData = getExifData(file);
        return exifData.getOrDefault("Device", null);
    }

    /**
     * 直接获取拍摄时间
     */
    public static String getDateTime(MultipartFile file) {
        Map<String, String> exifData = getExifData(file);
        return exifData.getOrDefault("DateTime", null);
    }

    /**
     * 直接获取位置信息
     */
    public static String getLocation(MultipartFile file) {
        Map<String, String> exifData = getExifData(file);
        return exifData.getOrDefault("Location", null);
    }

    /**
     * 检查图片是否包含位置信息
     */
    public static boolean hasLocationInfo(MultipartFile file) {
        return getLocation(file) != null;
    }
}