USE brice;

DROP TABLE IF EXISTS movies;

CREATE TABLE IF NOT EXISTS movies (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    rating DECIMAL(2,1) UNSIGNED NOT NULL,
    poster VARCHAR(255) NOT NULL,
    year INT UNSIGNED NOT NULL,
    genre VARCHAR(255) NOT NULL,
    director VARCHAR(255) NOT NULL,
    plot TEXT NOT NULL,
    actors VARCHAR(255) NOT NULL,
    imDbID VARCHAR(255) NOT NULL,
    runtime VARCHAR(255) NOT NULL,
    MPAA VARCHAR(255) NOT NULL,
    trailerURL VARCHAR(255) NOT NULL,
    favorite BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

insert into brice.movies (id, title, rating, poster, year, genre, director, plot, actors, imDbID, runtime, MPAA, trailerURL, favorite)
values  (3, 'Jaws', 8.1, 'https://imdb-api.com/images/original/MV5BMmVmODY1MzEtYTMwZC00MzNhLWFkNDMtZjAwM2EwODUxZTA5XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_Ratio0.6751_AL_.jpg', 1975, 'Adventure, Thriller', 'Steven Spielberg', 'It''s a hot summer on Amity Island, a small community whose main business is its beaches. When new Sheriff Martin Brody discovers the remains of a shark attack victim, his first inclination is to close the beaches to swimmers. This doesn''t sit well with Mayor Larry Vaughn and several of the local businessmen. Brody backs down to his regret as that weekend a young boy is killed by the predator. The dead boy''s mother puts out a bounty on the shark and Amity is soon swamped with amateur hunters and fisherman hoping to cash in on the reward. A local fisherman with much experience hunting sharks, Quint, offers to hunt down the creature for a hefty fee. Soon Quint, Brody and Matt Hooper from the Oceanographic Institute are at sea hunting the Great White shark. As Brody succinctly surmises after their first encounter with the creature, they''re going to need a bigger boat.', 'Roy Scheider, Robert Shaw, Richard Dreyfuss', 'tt0073195', '2h 4min', 'PG', 'https://www.youtube.com/watch?v=U1fu_sA7XhE', 1),
        (4, 'Up', 8.3, 'https://imdb-api.com/images/original/MV5BMTk3NDE2NzI4NF5BMl5BanBnXkFtZTgwNzE1MzEyMTE@._V1_Ratio0.6751_AL_.jpg', 2009, 'Animation, Adventure, Comedy', 'Pete Docter, Bob Peterson', 'As a boy, Carl Fredricksen wanted to explore South America and find the forbidden Paradise Falls. About 64 years later he gets to begin his journey along with Boy Scout Russell by lifting his house with thousands of balloons. On their journey, they make many new friends including a talking dog, and figure out that someone has evil plans. Carl soon realizes that this evildoer is his childhood idol.', 'Edward Asner, Jordan Nagai, John Ratzenberger', 'tt1049413', '1h 36min', 'PG', 'https://www.youtube.com/watch?v=ORFWdXl_zJ4', 0),
        (5, 'Saving Private Ryan', 8.6, 'https://imdb-api.com/images/original/MV5BZjhkMDM4MWItZTVjOC00ZDRhLThmYTAtM2I5NzBmNmNlMzI1XkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_Ratio0.6751_AL_.jpg', 1998, 'Drama, War', 'Steven Spielberg', 'Opening with the Allied invasion of Normandy on 6 June 1944, members of the 2nd Ranger Battalion under Cpt. Miller fight ashore to secure a beachhead. Amidst the fighting, two brothers are killed in action. Earlier in New Guinea, a third brother is KIA. Their mother, Mrs. Ryan, is to receive all three of the grave telegrams on the same day. The United States Army Chief of Staff, George C. Marshall, is given an opportunity to alleviate some of her grief when he learns of a fourth brother, Private James Ryan, and decides to send out 8 men (Cpt. Miller and select members from 2nd Rangers) to find him and bring him back home to his mother...', 'Tom Hanks, Matt Damon, Tom Sizemore', 'tt0120815', '2h 49min', 'R', 'https://www.youtube.com/watch?v=RYExstiQlLc', 0);