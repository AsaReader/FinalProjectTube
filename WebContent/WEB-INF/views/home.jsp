<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<c:import url="/includes/header.jsp" />
<c:import url="/includes/sidebar.jsp"></c:import>
<div class="content">
            <div class="grids">
                <h2>Latest Videos</h2>
                <div class="grid">
                    <div class="preview">
                        <a href="single.html"><img src="images/album.jpg" alt=""></a>
                        <div class="data">
                            <h3><a href="#1">Lorem PixelLorem PixelLoremPixelLoremPixel</a></h3>
                        </div>
                    </div>
                </div>
                <div class="grid">
                    <div class="preview">
                        <a href="single.html"><img src="images/album.jpg" alt=""></a>
                        <div class="data">
                            <h3><a href="#">Lorem Pixel</a></h3>
                        </div>
                    </div>
                </div>
                <div class="grid">
                    <div class="preview">
                        <a href="single.html"><img src="images/album.jpg" alt=""></a>
                        <div class="data">
                            <h3><a href="#">Lorem Pixel</a></h3>
                        </div>
                    </div>
                </div>
                <div class="clearFloat"></div>
            </div>
            <div class="more">
                <a href="#">More</a>
            </div>
            <div class="grids">
                <h2>Most Watched</h2>
                <div class="grid">
                    <div class="preview">
                        <a href="#"><img src="images/album.jpg" alt=""></a>
                        <div class="data">
                            <h3><a href="#1">Lorem PixelLorem PixelLoremPixelLoremPixel</a></h3>
                        </div>
                    </div>
                </div>
                <div class="grid">
                    <div class="preview">
                        <a href="#"><img src="images/album.jpg" alt=""></a>
                        <div class="data">
                            <h3><a href="#">Lorem Pixel</a></h3>
                        </div>
                    </div>
                </div>
                <div class="grid">
                    <div class="preview">
                        <a href="#"><img src="images/album.jpg" alt=""></a>
                        <div class="data">
                            <h3><a href="#">Lorem Pixel</a></h3>
                        </div>
                    </div>
                </div>
                <div class="clearFloat"></div>
            </div>
            <div class="more">
                <a href="#">More</a>
            </div>
</div>

<c:import url="/includes/footer.jsp" />