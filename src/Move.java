class Move {
	/* *************************************** */
	// write your code here
	private Box box;

	public Move(int _size) {
		box = new Box(_size, 0);
	}
	public void addBox(Box _box) {
		this.box.addItem(_box);
	}

	// @desc: Finds item number by using its name.
	// @param: ItemName as String
	// @return: integer type
	public int find(String itemName) {
		int number = 1;
		while (number <= Integer.MAX_VALUE) {
			Box currentBox = getBox(this.box, number);
			if (currentBox == null) {
				break;
			}
			for (Item item : currentBox.getItems()) {
				if (item instanceof SingleObject && item.getName().equals(itemName)) {
					return number;
				} else if (item instanceof Box) {
					number++;
				}
			}
			number++;
		}
		return number;
	}

	// @desc: Gets box by using its number.
	// @param: Box and its number.
	// @return: Box type
	private Box getBox(Box box, int number) {
		if (box == null || number <= 0) {
			return null;
		}
		if (number == 1) {
			return box;
		}
		for (Item item : box.getItems()) {
			if (item instanceof Box) {
				number--;

				if (number == 1) {
					return (Box) item;
				}
			}
		}
		return null;
	}

	// @desc: Displays all content by using Box and items.
	// @param: Box and its size.
	// @return: void
	private void displayContentInfo(Box box, int len) {
		for (Item item : box.getItems()) {
			if (item instanceof SingleObject) {
				System.out.println(item.getName());
			} else if (item instanceof Box) {
				displayContentInfo((Box) item, len + 1);
			}
		}
	}
	public void print() {
		displayContentInfo(box, 0);
	}
	/* *************************************** */

	public static void main(String[] args) {
		// We create a move that will hold 2 main boxes
		Move move = new Move(2);

		/*
		 * We create and then fill 3 boxes
		 * Arguments of the constructor of Box:
		 * argument 1: number of items (simple items/objects or box) that the box can hold
		 * argument 2: box number
		 */

		// box 1 contains scissors
		Box box1 = new Box(1, 1);
		box1.addItem(new SingleObject("scissors"));

		// box 2 contains one book
		Box box2 = new Box(1, 2);
		box2.addItem(new SingleObject("book"));

		// box 3 contains one compass
		// and one box containing one scarf
		Box box3 = new Box(2, 3);
		box3.addItem(new SingleObject("compass"));
		Box box4 = new Box(1, 4);
		box4.addItem(new SingleObject("scarf"));
		box3.addItem(box4);

		// We add the three boxes to the first box of move - see below
		Box box5 = new Box(3, 5);
		box5.addItem(box1);
		box5.addItem(box2);
		box5.addItem(box3);

		// We add one box containing 3 objects to move
		Box box6 = new Box(3, 6);
		box6.addItem(new SingleObject("pencils"));
		box6.addItem(new SingleObject("pens"));
		box6.addItem(new SingleObject("rubber"));

		// We add the two most external boxes to the move
		move.addBox(box5);
		move.addBox(box6);

		// We print all the contents of the move
		move.print();

		// We print the number of the outermost cardboard containing the item "scarf"
		System.out.println("The sarf is in the cardboard number " + move.find("scarf"));
	}
}