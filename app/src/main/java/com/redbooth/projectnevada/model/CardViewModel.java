/*
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.redbooth.projectnevada.model;

import com.redbooth.projectnevada.R;

import java.io.Serializable;

public class CardViewModel implements Serializable {
    private int upwardResourceId;
    private int downwardResourceId;
    private CardStatus status;

    public CardViewModel() {
        this.status = CardStatus.UPWARDS;
        this.downwardResourceId = R.drawable.cover_big;
        this.upwardResourceId = R.drawable.card01_big;
    }

    public int getDownwardResourceId() { return downwardResourceId; }

    public int getUpwardResourceId() { return upwardResourceId; }

    public CardStatus getStatus() { return status; }

    public void setUpwardResourceId(int upwardResourceId) {
        this.upwardResourceId = upwardResourceId;
    }

    public void setDownwardResourceId(int downwardResourceId) {
        this.downwardResourceId = downwardResourceId;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public enum CardStatus {
        UPWARDS,
        DOWNWARDS
    }
}
