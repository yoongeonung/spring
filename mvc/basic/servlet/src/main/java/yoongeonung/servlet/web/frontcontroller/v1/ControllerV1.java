package yoongeonung.servlet.web.frontcontroller.v1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControllerV1 {

  void process(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException;
}
