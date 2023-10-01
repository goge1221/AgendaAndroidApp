package goje.contactsapp.ui.agenda

import android.content.Context
import goje.contactsapp.entity.ContactElement

interface AgendaObserver {
    fun updateContactsList(context: Context, updatedList: List<ContactElement>)
    fun updateContactsListFromFilter(updatedList: List<ContactElement>)
}