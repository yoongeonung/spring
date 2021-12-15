package jp.ac.hal.yoongeonung.spring_mvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    /**
     * 요청매핑 - API예시
     */

    /**
     * GET /mapping/users
     */
    @GetMapping
    public String users() {
        return "get users";
    }

    /**
     * POST /mapping/users
     */
    @PostMapping
    public String addUsers() {
        return "post user";
    }

    /**
     * GET /mapping/users/{userId}
     */
    // 경로변수
    @GetMapping("/{userId}")
    public String findUser(@PathVariable Long userId) {
        return "get userId= " + userId;
    }

    // 요청파라터
    @GetMapping("/param")
    public String findUser2(@RequestParam("userId") String userId) {
        return "get requestParam userId= " + userId;
    }

    /**
     * PATCH /mapping/users/{userId}
     */
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable Long userId) {
        return "update userId = " + userId;
    }

    /**
     * DELETE /mapping/users/{userId}
     */
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        return "delete userId = " + userId;
    }
}
