# Requirements #

_This document contains requirements for Shopping list application_


---


## Use cases ##

## Screens ##

<u><b>Add new item</b></u>

A good can be described with 2 main attributes: name (milk, eggs, phone, etc.)
and picture. So this screen will be used to populate user library with goods.
User must be able to specify good name and picture of good.
Add name is easy - just textbox. But picture can be selected by many ways:

  * select image from media library;
  * take a photo;
  * search on the web (at the flickr for example).

Ideally we need all those possibilities. But for now I think it is ok
to pick image from media library.

When we get the image we need to store it somewhere. I believe it must be
file system. In db we will store only index. And also we need to scale image
depending on current user screen resolution. To be truth, we need at least 2-3
copies of image: small one (for icons in the list), bigger (for grid view) and
big for kaleidoscope view.

<u><b>View goods library</b></u>

Screen is used to view all goods at the library. The goods can be represents
in many ways:

  * list;
  * grid;
  * kaleidoscope (I think this is not very useful, but this attract peoples. This is cool!).

Also user can apply some sorting for goods:

  * by using count;
  * by name;
  * by favorites (user can add stars to good?);
  * by type or category (food, electro, garden).

And, of course, user can perform quick search by name.