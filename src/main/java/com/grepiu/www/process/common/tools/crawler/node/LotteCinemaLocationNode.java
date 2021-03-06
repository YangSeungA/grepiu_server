package com.grepiu.www.process.common.tools.crawler.node;

import com.google.common.collect.Lists;
import com.grepiu.www.process.common.tools.crawler.domain.CinemaLocation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

/**
 *
 * 시네마 주소를 크롤링한다.
 *
 */
@Slf4j
public class LotteCinemaLocationNode extends BaseNode<CinemaLocation> {

  @Override
  public List<CinemaLocation> execute(ExecuteOption executeOption) {
    // 크롬 초기화
    initChromeRemote("http://www.lottecinema.co.kr/LCHS/Contents/Cinema/charlotte-special-cinema.aspx", executeOption);
    // return 데이터 타입 Set
    List<CinemaLocation> cinemaNodeList = Lists.newArrayList();
    getDriver().findElements(By.cssSelector("[class^=depth]")).forEach(v->{
      log.info("v : {} ",v.getTagName());
      v.findElements(By.cssSelector("ul")).forEach(li -> {
        elementClick(li.findElement(By.cssSelector("li")));
        log.info("d : {}", li.getText());
      });
    });
    quit();
    return cinemaNodeList;
  }
}
