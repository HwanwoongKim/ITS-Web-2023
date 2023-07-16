package team.itsweb.board.controller;

import  org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.itsweb.board.entity.Board;
import team.itsweb.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.File;
import java.io.FileNotFoundException;
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
    @GetMapping("/")
    public String home() {
        return "Welcome to ITS!";
    }
    @PostMapping("/board/writepro")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Board boardwritePro(@RequestBody Board board){
        boardService.write(board);

        return board;
    }

    @GetMapping("/board/viewlist")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ResponseBody
    public List<Board> boardList(){
        return boardService.boardList();
    }

    @GetMapping("board/viewPost") //localhost:8090/board/view?id=1
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ResponseBody
    public Board boardView(Integer id){
        return boardService.boardView(id);
    }

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> resouceFileDownload(@PathVariable String fileName) {
        try {
            Resource resource = resourceLoader.getResource("classpath:static/"+ fileName);
            File file = resource.getFile();	//파일이 없는 경우 fileNotFoundException error가 난다.

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,file.getName())	//다운 받아지는 파일 명 설정
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))	//파일 사이즈 설정
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString())	//바이너리 데이터로 받아오기 설정
                    .body(resource);	//파일 넘기기
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(null);
        } catch (Exception e ) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}