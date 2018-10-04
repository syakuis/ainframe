package org.ainframe.webmvc.banner;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 7.
 */
@Slf4j
public class Banner {
    public Banner() {
        print();
    }

    private void print() {
        StringBuilder print = new StringBuilder();
//        print.append("\n_____________________________________________________\n");

        try (
            InputStream input = getClass().getResourceAsStream("/ainframe/banner.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                print.append(line).append("\n");
            }
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
        }

//        String version = "1.0.0";
//
//        print.append(" ainframe with spring-boot            version ")
//            .append(version)
//            .append("  \n")
//            .append("                                                     \n")
//            .append("_____________________________________________________\n\n");

        System.out.println(print.toString());
    }
}
