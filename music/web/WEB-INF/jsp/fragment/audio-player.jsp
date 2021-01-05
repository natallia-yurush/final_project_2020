<%--
  Created by IntelliJ IDEA.
  User: Natallia Yurush
  Date: 05-Jan-21
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!-- Begin | Audio Player [[ Find at scss/framework/base/audio-player/audio-player.scss ]] -->
<div id="audioPlayer" class="player-primary">

    <!-- Begin | Audio Player Progress -->
    <div id="progress-container">
        <input type="range" class="amplitude-song-slider">
        <progress class="audio-progress audio-progress--played amplitude-song-played-progress"></progress>
        <progress class="audio-progress audio-progress--buffered amplitude-buffered-progress" value="0"></progress>
    </div>
    <!-- End | Audio Player Progress -->

    <!-- Begin | Audio -->
    <div class="audio">
        <div class="song-image"><img data-amplitude-song-info="cover_art_url" src="${pageContext.request.contextPath}/assets/images/cover/small/1.jpg" alt=""></div>
        <div class="song-info pl-3">
            <span class="song-name d-inline-block text-truncate" data-amplitude-song-info="name"></span><!--TODO: здесь нейм это название песни-->
            <span class="song-artists d-block text-muted" data-amplitude-song-info="artist"></span>
        </div>
    </div>
    <!-- End | Audio -->

    <!-- Begin | Audio Controls -->
    <div class="audio-controls">
        <!--<div class="audio-controls&#45;&#45;left d-flex mr-auto">
            <button class="btn btn-icon-only amplitude-repeat"><i class="ion-md-sync"></i></button>
        </div>-->
        <div class="audio-controls--main d-flex">
            <button class="btn btn-icon-only amplitude-prev"><i class="ion-md-skip-backward"></i></button>
            <button class="btn btn-air btn-pill btn-default btn-icon-only amplitude-play-pause">
                <i class="ion-md-play"></i>
                <i class="ion-md-pause"></i>
            </button>
            <button class="btn btn-icon-only amplitude-next"><i class="ion-md-skip-forward"></i></button>
        </div>
        <!--<div class="audio-controls&#45;&#45;right d-flex ml-auto">
            <button class="btn btn-icon-only amplitude-shuffle amplitude-shuffle-off"><i class="ion-md-shuffle"></i></button>
        </div>-->
    </div>
    <!-- End | Audio Controls -->

    <!-- Begin | Audio Info -->
    <!--TODO: разобраться что здесь происходит!-->
    <div class="audio-info d-flex align-items-center pr-4">
                    <span class="mr-4">
                        <span class="amplitude-current-minutes" ></span>:<span class="amplitude-current-seconds"></span> /
                        <span class="amplitude-duration-minutes"></span>:<span class="amplitude-duration-seconds"></span>
                    </span>
        <div class="audio-volume dropdown">
            <button class="btn btn-icon-only" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="ion-md-volume-low"></i>
            </button>
            <div class="dropdown-menu dropdown-menu-right volume-dropdown-menu">
                <input type="range" class="amplitude-volume-slider" value="100">
            </div>
        </div>

        <div class="dropleft">
            <button class="btn btn-icon-only" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="la la-ellipsis-v"></i>
            </button>
            <ul class="dropdown-menu">
                <li class="dropdown-item">
                    <a href="javascript:void(0);" class="dropdown-link">
                        <i class="la la-heart-o"></i> <span>Favorite</span>
                    </a>
                </li>
                <li class="dropdown-item">
                    <a href="javascript:void(0);" class="dropdown-link">
                        <i class="la la-plus"></i> <span>Add to Playlist</span>
                    </a>
                </li>
            </ul>
        </div>
        <button class="btn btn-icon-only" id="playList"><i class="ion-md-musical-note"></i></button>
    </div>
    <!-- End | Audio Info -->

</div>
<!-- End | Audio Player -->
