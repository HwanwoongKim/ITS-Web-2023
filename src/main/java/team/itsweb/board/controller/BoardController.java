package team.itsweb.board.controller;

import team.itsweb.board.entity.Board;
import team.itsweb.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.persistence.criteria.CriteriaBuilder;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") //localhost:8090/board/write
    public String boardwriteForm(){

        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardwritePro(Board board){

        System.out.println(board.getTitle());

        boardService.write(board);

        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){

        model.addAttribute("list", boardService.boardList());

        return "boardlist";
    }

    @GetMapping("board/view") //localhost:8090/board/view?id=1
    public String boardView(Model model, Integer id

    ){

        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }
}