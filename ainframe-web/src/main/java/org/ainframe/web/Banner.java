package org.ainframe.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 7.
 */
@Slf4j
public class Banner {
    public Banner() {
        print();
    }

    public void print() {
        StringBuilder print = new StringBuilder();
        print.append("\n_________________________________________________________________________________\n");

        try {
            InputStream input = getClass().getResourceAsStream("/ainframe-banner.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;
            while ((line = reader.readLine()) != null) {
                print.append(line).append("\n");
            }
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
        }

        String version = "1.0.0";

        print.append("                                                                  version ")
            .append(version)
            .append("  \n")
            .append("                                                                                 \n")
            .append("                                                                                 \n")
            .append("                           Frontend X Backend (Fxb) by 52572 49437 44512         \n")
            .append("                                                                                 \n")
            .append("_________________________________________________________________________________\n\n");

        System.out.println(print.toString());
    }
}
