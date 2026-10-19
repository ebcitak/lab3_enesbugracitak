/**
 * Represents the doubly linked list for the music playlist.
 */
public class MusicPlaylist {

    // private instances of the doubly linked list
    private SongNode head;
    private SongNode tail;
    private SongNode current;

    // constructor: The head, tail and current should be initialized to null in the beginning
    public MusicPlaylist() {
        this.head = null;
        this.tail = null;
        this.current = null;
    }

    // Add a song to the end of the playlist
    public void addSong(String title) {
        // 1. Create a new SongNode
        SongNode newNode = new SongNode(title);

        // 2. If the playlist is empty, make the new node head, tail and current
        if (head == null) {
            head = newNode;
            tail = newNode;
            current = newNode;
        }
        // 3. Otherwise, add the new node after tail
        else {
            // 4. Update both next and prev pointer
            tail.next = newNode;
            newNode.prev = tail;
            // 5. Update tail
            tail = newNode;
        }
    }

    // Remove a song from the playlist
    public void removeSong(String title) {
        // 1. Search for the song
        SongNode temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.title.equals(title)) {
                found = true;
                break;
            }
            temp = temp.next;
        }

        // 2. If the song does not exist, display a message
        if (!found) {
            System.out.println("The song isn't in the playlist");
            return;
        }

        System.out.println("Removed: " + temp.title);

        // 5. Handle removing head
        if (temp == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
        }
        // 6. Handle removing tail
        else if (temp == tail) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
        }
        // 3 & 4. Update the previous node's next pointer and next node's prev pointer (removing middle)
        else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        // 7. Handle removing the current song
        if (temp == current) {
            if (current.next != null) {
                current = current.next;
            } else if (current.prev != null) {
                current = current.prev;
            } else {
                current = null; // List becomes empty
            }
        }

        // If the list became empty after removal, ensure tail is null
        if (head == null) {
            tail = null;
        }
    }

    // Move to the next song
    public void nextSong() {
        // If current is not null and current.next exists, move current to current.next.
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Playing: " + current.title);
        } else {
            // Otherwise, display:
            System.out.println("Already at the last song of the playlist.");
        }
    }

    // Move to the previous song
    public void previousSong() {
        // If current is not null and current.prev exists, move current to current.prev.
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Playing: " + current.title);
        } else {
            // Otherwise, display:
            System.out.println("Already at the first song of the playlist.");
        }
    }

    // Display the current song
    public void displayCurrentSong() {
        // Display the title of the current song.
        if (current != null) {
            System.out.println("Current Song: " + current.title);
        } else {
            System.out.println("Playlist is empty.");
        }
    }

    // Display all songs
    public void displayPlaylist() {
        // Start from head and follow next pointer.
        SongNode temp = head;
        while (temp != null) {
            System.out.print(temp.title + " <-> ");
            temp = temp.next;
        }
        System.out.println("END");
    }
}