package team.itsweb.board.service;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Path;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import team.itsweb.board.entity.Board;
import team.itsweb.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글 작성 처리
    public void write(Board board){

        boardRepository.save(board);
    }

    //게시글 리스트 처리
    public List<Board> boardList(){

        return boardRepository.findAll();
    }

    //특정 게시글 불러오기
    public Board boardView(Integer id) {

        return boardRepository.findById(id).get();
    }


}