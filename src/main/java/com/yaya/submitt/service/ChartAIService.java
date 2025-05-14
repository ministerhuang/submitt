//package com.yaya.submitt.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.yaya.submitt.pojo.ApiJsonMessage;
//import com.yaya.submitt.pojo.NewsResponse;
//import com.yaya.submitt.pojo.dto.ChatSummaryDto;
//import com.yaya.submitt.repository.ChartAIRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@Service
//public class ChartAIService implements IChartAIService{
//    @Autowired
//    private ChartAIRepository chartAIRepository;
//
//    @Override
//    public String add(String ai_input){
//        String API_KEY = "AIzaSyA_P7BlMztkhmlFZImJpnnHc29aovY00EQ";
//        String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent?key="+API_KEY;
//
//        RestTemplate restTemplate = new RestTemplate();
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        String prompt = "You are the intelligent assistant of the website Iyakah: https://iyakah-fit5120.vercel.app\n" +
//                "\n" +
//                "The goal of this website is to help users enhance their digital citizenship, identify misinformation, and detect AI-generated or manipulated content. The website includes the following features:\n" +
//                "\n" +
//                "1.Home: https://iyakah-fit5120.vercel.app/index.html\n" +
//                "   - Overview and navigation of the platform.\n" +
//                "\n" +
//                "2.Website Legitimacy Check: https://iyakah-fit5120.vercel.app/website-verification.html\n" +
//                "   - Users can enter a URL to check if the website is trustworthy, whether it uses HTTPS, and if it's blacklisted.\n" +
//                "\n" +
//                "3.News Bias & AI Text Detection: https://iyakah-fit5120.vercel.app/news-verification.html\n" +
//                "   - Analyze media and textual bias, and determine whether a news article was written by AI.\n" +
//                "\n" +
//                "4.Image Verification: https://iyakah-fit5120.vercel.app/image-verification.html\n" +
//                "   - Users can upload an image to check whether it's AI-generated (e.g., by Midjourney or DALL·E).\n" +
//                "\n" +
//                "5.FAQ: https://iyakah-fit5120.vercel.app/faq.html\n" +
//                "   - Common questions and explanations about the platform.\n" +
//                "\n" +
//                "6.Digital Citizenship Quiz: https://iyakah-fit5120.vercel.app/quiz.html\n" +
//                "   - A 5-question quiz (randomly selected from 15) to test users’ understanding of digital citizenship.\n" +
//                "\n" +
//                "As the assistant, your job is to:\n" +
//                "- Help users understand how to use the website’s features,\n" +
//                "- Direct them to the correct page,\n" +
//                "- Explain the meaning of detection results,\n" +
//                "- And promote awareness of digital safety and responsible online behavior.\n" +
//                "\n" +
//                "Always respond in a helpful and concise manner, referring to the site features when appropriate.";
//        String history = "";
//        String question = "User new question is : "+ai_input+" Please base on the above and history to answer it";
//
//        List<ChatSummaryDto> listSummary = chartAIRepository.findRecentQuestions(ai_input);
//
//        if (listSummary.size() == 0){
//
//        }
//
//        String jsonResponse = restTemplate.postForObject(API_URL, ai_input, String.class);
//
//        // 2. 解析 JSON 数据
//        String longTextResponse = jsonResponse;
//
//
//        return API_URL;
//    }
//}
