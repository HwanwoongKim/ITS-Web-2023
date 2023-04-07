package team.itsweb.board.controller;

import org.springframework.web.bind.annotation.*;
import team.itsweb.board.entity.Board;
import team.itsweb.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    /*
    @GetMapping("/board/write") //localhost:8090/board/write

    public String boardwriteForm(){

        return "boardwrite";
    }
    */

    @PostMapping("/board/writepro")
    public Board boardwritePro(@RequestBody Board board){
        boardService.write(board);

        return board;
    }

    @GetMapping("/board/viewlist")
    @ResponseBody
    public List<Board> boardList(){
        return boardService.boardList();
    }

    @GetMapping("board/viewPost") //localhost:8090/board/view?id=1
    @ResponseBody
    public Board boardView(Integer id){
        return boardService.boardView(id);
    }
}